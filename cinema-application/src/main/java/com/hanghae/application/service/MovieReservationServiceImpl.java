package com.hanghae.application.service;

import com.hanghae.application.dto.ApiResponse;
import com.hanghae.application.dto.MovieReservationRequestDto;
import com.hanghae.application.port.in.MovieReservationService;
import com.hanghae.application.port.out.MemberRepositoryPort;
import com.hanghae.application.port.out.ScreeningScheduleRepositoryPort;
import com.hanghae.application.port.out.TicketReservationRepositoryPort;
import com.hanghae.domain.model.Member;
import com.hanghae.domain.model.ScreeningSchedule;
import com.hanghae.domain.model.TicketReservation;
import com.hanghae.domain.model.enums.ScreenSeat;
import com.hanghae.domain.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieReservationServiceImpl implements MovieReservationService {
    private final TicketReservationRepositoryPort ticketReservationRepositoryPort;
    private final ScreeningScheduleRepositoryPort screeningScheduleRepositoryPort;
    private final MemberRepositoryPort memberRepositoryPort;
    private final ReservationService reservationService;

    @Override
    public ApiResponse<Void> saveMovieReservation(MovieReservationRequestDto requestDto) {
        //영화 스케줄 가져오기
        ScreeningSchedule screeningSchedule = screeningScheduleRepositoryPort.findById(requestDto.scheduleId());
        Member member = memberRepositoryPort.findById(requestDto.memberId());

        // TODO :: 해당 상영관 좌석 최대값 가져오기

        // TODO :: 현재 예매한 좌석 수 확인하기

        // TODO :: 1인당 5개로 예매 제한하기

        // 선탹 좌석기준으로 예약정보 가져오기
        List<TicketReservation> ticketReservations =
                reservationService.generateTicketReservationsBySeat(
                        screeningSchedule, member, requestDto.screenSeat(), requestDto.seatCount());

        // TODO :: 중복 예매 내역 확인하기

        //좌석 저장
        List<TicketReservation> savedTicketReservations = ticketReservationRepositoryPort.saveMovieReservations(ticketReservations);
        return ApiResponse.of("Success", 201);
    }
}
