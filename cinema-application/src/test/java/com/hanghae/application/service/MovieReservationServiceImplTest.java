package com.hanghae.application.service;

import com.hanghae.application.TestDataFactory;
import com.hanghae.application.dto.ApiResponse;
import com.hanghae.application.dto.request.MovieReservationRequestDto;
import com.hanghae.application.enums.ErrorCode;
import com.hanghae.application.exception.CustomRequestException;
import com.hanghae.application.port.out.message.MessagePort;
import com.hanghae.application.port.out.redis.RedisRateLimitPort;
import com.hanghae.application.port.out.redis.RedissonLockPort;
import com.hanghae.application.port.out.repository.MemberRepositoryPort;
import com.hanghae.application.port.out.repository.ScreenSeatLayoutRepositoryPort;
import com.hanghae.application.port.out.repository.ScreeningScheduleRepositoryPort;
import com.hanghae.application.port.out.repository.TicketReservationRepositoryPort;
import com.hanghae.domain.model.Member;
import com.hanghae.domain.model.ScreenSeatLayout;
import com.hanghae.domain.model.ScreeningSchedule;
import com.hanghae.domain.model.TicketReservation;
import com.hanghae.domain.model.enums.ScreenSeat;
import com.hanghae.domain.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieReservationServiceImplTest {

    @InjectMocks
    private MovieReservationServiceImpl movieReservationService;

    @Mock
    private TicketReservationRepositoryPort ticketReservationRepositoryPort;
    @Mock
    private ScreeningScheduleRepositoryPort screeningScheduleRepositoryPort;
    @Mock
    private ScreenSeatLayoutRepositoryPort screenSeatLayoutRepositoryPort;
    @Mock
    private MemberRepositoryPort memberRepositoryPort;
    @Mock
    private MessagePort messagePort;
    @Mock
    private ReservationService reservationService;
    @Mock
    private RedissonLockPort redissonLockPort;
    @Mock
    private RedisRateLimitPort redisRateLimitPort;

    private MovieReservationRequestDto requestDto;
    private ScreeningSchedule screeningSchedule;
    private Member member;
    private ScreenSeatLayout screenSeatLayout;
    private List<ScreenSeat> selectedSeats;
    private List<TicketReservation> ticketReservations;

    @BeforeEach
    void setUp() {
        requestDto = TestDataFactory.createMovieReservationRequestDto();
        screeningSchedule = TestDataFactory.createScreeningSchedule();
        member = TestDataFactory.createMember();
        screenSeatLayout = TestDataFactory.createScreenSeatLayout();
        selectedSeats = ScreenSeat.getSelectedConnectedSeats(ScreenSeat.A01, 2);
        ticketReservations = TestDataFactory.createTicketReservations();

        // 예상 동작 정의
        // 필요하지 않은 Stubbing이 있어도 예외 없이 테스트가 진행되게 lenient() 사용
        lenient().when(screeningScheduleRepositoryPort.findById(anyLong())).thenReturn(screeningSchedule);
        lenient().when(memberRepositoryPort.findById(anyLong())).thenReturn(member);
        lenient().when(screenSeatLayoutRepositoryPort.findBySeatRowAndScreenId(anyString(), anyLong())).thenReturn(screenSeatLayout);
        lenient().when(ticketReservationRepositoryPort.countByScreeningScheduleIdAndMemberId(anyLong(), anyLong())).thenReturn(0);
        lenient().when(ticketReservationRepositoryPort.countByScheduleIdAndScreenSeats(anyLong(), anyList())).thenReturn(0);
        lenient().when(reservationService.createTicketReservations(any(), any(), any(), any(), anyInt())).thenReturn(ticketReservations);
        lenient().when(redisRateLimitPort.canReserve(anyLong(), anyLong())).thenReturn(true);
        lenient().when(redissonLockPort.executeWithSeatsLocks(anyLong(), anyList(), any())).thenAnswer(invocation -> {
            Supplier<ApiResponse<Void>> supplier = invocation.getArgument(2);
            return supplier.get();
        });
    }

    @Test
    @DisplayName("정상적인 예매 성공 테스트")
    void saveMovieReservationSuccess() {
        ApiResponse<Void> response = movieReservationService.saveMovieReservation(requestDto);

        // 응답 성공 확인
        assertTrue(response.success());

        //ticketReservationRepositoryPort.saveMovieReservations 메서드가 1회 호출 되었는지 확인
        verify(ticketReservationRepositoryPort, times(1)).saveMovieReservations(anyList());

        //messagePort.sendMessage 메서드가 1회 호출 되었는지 확인
        verify(messagePort, times(1)).sendMessage(anyString());
    }

    @Test
    @DisplayName("동일 시간대 예매 제한 초과 시 예외 처리 테스트")
    void saveMovieReservationTooManyRequests() {
        when(redisRateLimitPort.canReserve(anyLong(), anyLong())).thenReturn(false);

        ApiResponse<Void> response = movieReservationService.saveMovieReservation(requestDto);

        // 응답 실패 확인
        assertFalse(response.success());

        //ticketReservationRepositoryPort.saveMovieReservations 메서드가 호출 되지 않았는지 확인
        verify(ticketReservationRepositoryPort, never()).saveMovieReservations(anyList());
    }

    @Test
    @DisplayName("이미 예매된 좌석일 경우 예외 발생 테스트")
    void saveMovieReservationSeatAlreadyReserved() {
        when(ticketReservationRepositoryPort.countByScheduleIdAndScreenSeats(anyLong(), anyList())).thenReturn(1);

        //validateSeatAvailability 인자로 받은 값이 0보다 크면 예외처리, 아닐 경우 그냥 통과
        doAnswer(invocation -> {
            int count = invocation.getArgument(0);
            if (count > 0) {
                throw new IllegalArgumentException("선택한 좌석은 이미 예매되었습니다.");
            }
            return null;
        }).when(reservationService).validateSeatAvailability(anyInt());

        CustomRequestException exception = assertThrows(CustomRequestException.class, () ->
                movieReservationService.saveMovieReservation(requestDto));

        // 응답 메시지 비교
        assertEquals("선택한 좌석은 이미 예매되었습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("예매좌석 5개 초과시 예외 발생 테스트")
    void saveMovieReservationSeatLimitExceeded() {
        when(ticketReservationRepositoryPort.countByScreeningScheduleIdAndMemberId(anyLong(), anyLong())).thenReturn(5);

        //validateReservationSeatLimit 인자로 받은 값의 합이 5보다 크면 예외처리, 아닐 경우 그냥 통과
        doAnswer(invocation -> {
            int reservedTicketCount = invocation.getArgument(0);
            int seatCount = invocation.getArgument(1);

            if (reservedTicketCount + seatCount > 5) {
                throw new IllegalArgumentException("상영시간별 예매는 최대 5개까지 할 수 있습니다.");
            }

            return null; // void 메서드이므로 null 반환
        }).when(reservationService).validateReservationSeatLimit(anyInt(), anyInt());


        CustomRequestException exception = assertThrows(CustomRequestException.class, () ->
                movieReservationService.saveMovieReservation(requestDto));

        // 응답 메시지 확인
        assertEquals("상영시간별 예매는 최대 5개까지 할 수 있습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("Redisson Lock을 획득하지 못했을 경우 예외 처리 테스트")
    void saveMovieReservationLockFailed() {
        // 기존 Mock 설정 제거
        Mockito.reset(redissonLockPort);

        // executeWithSeatsLocks 예상 동작 다시 정의
        when(redissonLockPort.executeWithSeatsLocks(anyLong(), anyList(), any()))
                .thenThrow(new CustomRequestException("현재 좌석을 다른 사용자가 예매 처리 중입니다.", ErrorCode.SEAT_NOT_AVAILABLE));

        CustomRequestException exception = assertThrows(CustomRequestException.class, () ->
                movieReservationService.saveMovieReservation(requestDto));

        // 응답 메시지 확인
        assertEquals("현재 좌석을 다른 사용자가 예매 처리 중입니다.", exception.getMessage());
    }
}
