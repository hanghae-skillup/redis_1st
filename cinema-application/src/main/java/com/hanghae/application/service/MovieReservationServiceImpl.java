package com.hanghae.application.service;

import com.hanghae.application.dto.ApiResponse;
import com.hanghae.application.dto.MovieReservationRequestDto;
import com.hanghae.application.enums.HttpStatusCode;
import com.hanghae.application.port.in.MovieReservationService;
import com.hanghae.application.port.out.*;
import com.hanghae.domain.model.Member;
import com.hanghae.domain.model.ScreenSeatLayout;
import com.hanghae.domain.model.ScreeningSchedule;
import com.hanghae.domain.model.TicketReservation;
import com.hanghae.domain.model.enums.ScreenSeat;
import com.hanghae.domain.service.ReservationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieReservationServiceImpl implements MovieReservationService {
    private final TicketReservationRepositoryPort ticketReservationRepositoryPort;
    private final ScreeningScheduleRepositoryPort screeningScheduleRepositoryPort;
    private final ScreenSeatLayoutRepositoryPort screenSeatLayoutRepositoryPort;
    private final MemberRepositoryPort memberRepositoryPort;
    private final MessagePort messagePort;
    private final ReservationService reservationService;
    private final RedissonLockPort redissonLockPort; // Redisson 분산락 사용

    @Override
    @Transactional
    public ApiResponse<Void> saveMovieReservation(MovieReservationRequestDto requestDto) {
        Long scheduleId = requestDto.scheduleId();
        Long memberId = requestDto.memberId();
        int seatCount = requestDto.seatCount();
        ScreenSeat screenSeat = requestDto.screenSeat();

        // 예매할 좌석 목록 가져오기
        List<ScreenSeat> selectedSeats = ScreenSeat.getSelectedConnectedSeats(screenSeat, seatCount);

        try {
            // 여러 좌석에 대해 Redisson 함수형 분산락 획득
            // 람다식(Supplier<T>)으로 받은 함수의 리턴값을 그대로 리턴
            return redissonLockPort.executeWithSeatsLocks(scheduleId, selectedSeats, () -> {
                // 영화 스케줄 가져오기
                ScreeningSchedule screeningSchedule = screeningScheduleRepositoryPort.findById(scheduleId);
                // 회원정보 조회
                Member member = memberRepositoryPort.findById(memberId);
                // 선택 상영관 좌석 최대값 가져오기
                ScreenSeatLayout screenSeatLayout = screenSeatLayoutRepositoryPort.findBySeatRowAndScreenId(screenSeat.getSeatRow(), screeningSchedule.getScreen().getScreenId());

                // 해당 회원의 예매내역 조회
                int reservedTicketCount = ticketReservationRepositoryPort.countByScreeningScheduleIdAndMemberId(scheduleId, memberId);
                // 예매 내역 5개 초과 검증
                reservationService.validateReservationSeatLimit(reservedTicketCount, seatCount);

                // 예매하려는 좌석에 대한 예매내역 조회
                int duplicateReservationCount = ticketReservationRepositoryPort.countByScheduleIdAndScreenSeats(scheduleId, selectedSeats);
                // 예매 좌석 중복 내역 확인하기
                reservationService.validateSeatAvailability(duplicateReservationCount);

                // 예매 내역 생성
                List<TicketReservation> ticketReservations = reservationService.createTicketReservations(screeningSchedule, member, screenSeat, screenSeatLayout, seatCount);

                // 예매 내역 저장
                ticketReservationRepositoryPort.saveMovieReservations(ticketReservations);

                //완료 메시지 전송 (비동기)
                messagePort.sendMessage("영화 예매가 완료 되었습니다.");

                return ApiResponse.of("예매가 완료 되었습니다.", HttpStatusCode.CREATED.getCode());
            });
        } catch (IllegalStateException e) {
            return ApiResponse.of("현재 좌석을 다른 사용자가 예매 처리 중입니다.", HttpStatusCode.CONFLICT.getCode());
        }
    }
}
