package com.example.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.application.dto.request.ReservationRequestDto;
import com.example.application.dto.response.ReservationResponseDto;
import com.example.application.lock.DistributedLockExecutor;
import com.example.application.port.in.MessageServicePort;
import com.example.application.port.out.MemberRepositoryPort;
import com.example.application.port.out.ReservationRepositoryPort;
import com.example.application.port.out.ReservationValidationPort;
import com.example.application.port.out.ScreeningRepositoryPort;
import com.example.application.port.out.ScreeningSeatRepositoryPort;
import com.example.domain.exception.CustomException;
import com.example.domain.exception.ErrorCode;
import com.example.domain.model.entity.Member;
import com.example.domain.model.entity.Movie;
import com.example.domain.model.entity.Reservation;
import com.example.domain.model.entity.Screening;
import com.example.domain.model.entity.ScreeningSeat;
import com.example.domain.model.entity.Seat;
import com.example.domain.model.entity.Theater;
import com.example.domain.model.valueObject.SeatNumber;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepositoryPort reservationRepositoryPort;
    @Mock
    private MemberRepositoryPort memberRepositoryPort;
    @Mock
    private ScreeningRepositoryPort screeningRepositoryPort;
    @Mock
    private ScreeningSeatRepositoryPort screeningSeatRepositoryPort;
    @Mock
    private ReservationValidationPort reservationValidationPort;
    @Mock
    private MessageServicePort messageServicePort;
    @Mock
    private DistributedLockExecutor distributedLockExecutor;

    @InjectMocks
    private ReservationService reservationService;

    private ReservationRequestDto requestDto;
    private Screening screening;
    private Member member;
    private ScreeningSeat screeningSeat;
    private Seat seat;
    private SeatNumber seatNumber;
    private Reservation reservation;
    private Movie movie;
    private Theater theater;

    @BeforeEach
    void setUp() {
        requestDto = new ReservationRequestDto(1L, 1L, List.of(1L, 2L));
        screening = mock(Screening.class);
        member = mock(Member.class);
        screeningSeat = mock(ScreeningSeat.class);
        seat = mock(Seat.class);
        seatNumber = mock(SeatNumber.class);
        reservation = mock(Reservation.class);
        movie = mock(Movie.class);
        theater = mock(Theater.class);
    }

    @DisplayName("예약이 정상적으로 처리될 경우 ReservationResponseDto를 반환하고 메시지를 전송한다.")
    @Test
    void givenValidRequest_whenCreate_thenReturnReservationDtoAndSendMessage() {
        // Given
        when(screeningRepositoryPort.findById(requestDto.screeningId())).thenReturn(Optional.of(screening));
        when(memberRepositoryPort.findById(requestDto.memberId())).thenReturn(Optional.of(member));
        when(screeningSeatRepositoryPort.findByScreeningAndSeatIds(any(), anyList()))
                .thenReturn(List.of(screeningSeat));
        when(screeningSeat.getSeat()).thenReturn(seat);
        when(screening.getMovie()).thenReturn(movie);
        when(screening.getTheater()).thenReturn(theater);
        when(theater.getName()).thenReturn("Test Theater");
        when(seat.getSeatNumber()).thenReturn(seatNumber);
        when(seatNumber.toString()).thenReturn("A1");
        when(distributedLockExecutor.executeWithLock(anyString(), anyLong(), anyLong(), any()))
                .thenReturn(reservation);
        when(reservation.getMember()).thenReturn(member);
        when(reservation.getScreening()).thenReturn(screening);
        when(member.getName()).thenReturn("Test User");

        // When
        ReservationResponseDto response = reservationService.create(requestDto);

        // Then
        assertNotNull(response);
        verify(messageServicePort, times(1)).send(anyString());
    }

    @DisplayName("예약 실패 시 예외가 발생한다.")
    @Test
    void givenLockFailure_whenCreate_thenThrowException() {
        // Given: 분산 락 획득 실패
        when(screeningRepositoryPort.findById(requestDto.screeningId())).thenReturn(Optional.of(screening));
        when(memberRepositoryPort.findById(requestDto.memberId())).thenReturn(Optional.of(member));
        when(screeningSeatRepositoryPort.findByScreeningAndSeatIds(any(), anyList()))
                .thenReturn(List.of(screeningSeat));
        when(screeningSeat.getSeat()).thenReturn(seat);
        when(distributedLockExecutor.executeWithLock(anyString(), anyLong(), anyLong(), any()))
                .thenReturn(reservation);

        when(distributedLockExecutor.executeWithLock(anyString(), anyLong(), anyLong(), any())).thenReturn(null);

        // When & Then
        assertThrows(CustomException.class, () -> reservationService.create(requestDto));
    }

    @DisplayName("최대 예약 가능 좌석 수 초과 시 예외 발생")
    @Test
    void givenTooManySeats_whenCreate_thenThrowException() {
        // Given: 회원이 이미 좌석을 예약한 상태
        when(screeningRepositoryPort.findById(requestDto.screeningId())).thenReturn(Optional.of(screening));
        when(memberRepositoryPort.findById(requestDto.memberId())).thenReturn(Optional.of(member));
        when(screeningSeatRepositoryPort.findByScreeningAndSeatIds(any(), anyList()))
                .thenReturn(List.of(screeningSeat));

        when(memberRepositoryPort.findById(requestDto.memberId())).thenReturn(Optional.of(member));
        when(reservationRepositoryPort.countByScreeningAndMember(any(), any())).thenReturn(5); // 5석 예약됨
        doThrow(new CustomException(ErrorCode.MAX_SEATS_EXCEEDED))
                .when(reservationValidationPort)
                .validateMaxSeatsPerScreening(anyInt(), anyInt());

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () -> reservationService.create(requestDto));
        assertEquals(ErrorCode.MAX_SEATS_EXCEEDED, exception.getErrorCode());
    }

    @DisplayName("연속되지 않은 좌석 선택 시 예외 발생")
    @Test
    void givenNonConsecutiveSeats_whenCreate_thenThrowException() {
        // Given: 좌석이 연속되지 않음
        when(screeningRepositoryPort.findById(requestDto.screeningId())).thenReturn(Optional.of(screening));
        when(memberRepositoryPort.findById(requestDto.memberId())).thenReturn(Optional.of(member));
        when(screeningSeatRepositoryPort.findByScreeningAndSeatIds(any(), anyList()))
                .thenReturn(List.of(screeningSeat));
        when(screeningSeat.getSeat()).thenReturn(seat);
        when(memberRepositoryPort.findById(requestDto.memberId())).thenReturn(Optional.of(member));
        doThrow(new CustomException(ErrorCode.SEATS_NOT_CONSECUTIVE))
                .when(reservationValidationPort)
                .validateSeatsAreConsecutive(anyList());

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () -> reservationService.create(requestDto));
        assertEquals(ErrorCode.SEATS_NOT_CONSECUTIVE, exception.getErrorCode());
    }

    @DisplayName("이미 예약된 좌석 포함 시 예외 발생")
    @Test
    void givenPartiallyReservedSeats_whenCreate_thenThrowException() {
        // Given: 일부 좌석이 이미 예약됨
        when(screeningRepositoryPort.findById(requestDto.screeningId())).thenReturn(Optional.of(screening));
        when(memberRepositoryPort.findById(requestDto.memberId())).thenReturn(Optional.of(member));
        when(screeningSeatRepositoryPort.findByScreeningAndSeatIds(any(), anyList()))
                .thenReturn(List.of(screeningSeat));

        when(screeningRepositoryPort.findById(requestDto.screeningId())).thenReturn(Optional.of(screening));
        when(memberRepositoryPort.findById(requestDto.memberId())).thenReturn(Optional.of(member));
        doThrow(new CustomException(ErrorCode.SEAT_ALREADY_RESERVED))
                .when(reservationValidationPort)
                .validateSeatsExist(anyList(), anyList());

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () -> reservationService.create(requestDto));
        assertEquals(ErrorCode.SEAT_ALREADY_RESERVED, exception.getErrorCode());
    }

    @Test
    @DisplayName("분산 락 획득 실패 시 예외 발생")
    void givenLockAcquisitionFailure_whenCreate_thenThrowException() {
        // Given: 분산 락 획득 실패
        when(screeningRepositoryPort.findById(requestDto.screeningId())).thenReturn(Optional.of(screening));
        when(memberRepositoryPort.findById(requestDto.memberId())).thenReturn(Optional.of(member));
        when(screeningSeatRepositoryPort.findByScreeningAndSeatIds(any(), anyList()))
                .thenReturn(List.of(screeningSeat));
        when(screeningSeat.getSeat()).thenReturn(seat);
        when(distributedLockExecutor.executeWithLock(anyString(), anyLong(), anyLong(), any()))
                .thenReturn(reservation);

        when(screeningRepositoryPort.findById(requestDto.screeningId())).thenReturn(Optional.of(screening));
        when(memberRepositoryPort.findById(requestDto.memberId())).thenReturn(Optional.of(member));
        when(distributedLockExecutor.executeWithLock(anyString(), anyLong(), anyLong(), any()))
                .thenThrow(new CustomException(ErrorCode.DISTRIBUTED_LOCK_FAILURE));

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () -> reservationService.create(requestDto));
        assertEquals(ErrorCode.DISTRIBUTED_LOCK_FAILURE, exception.getErrorCode());
    }

}
