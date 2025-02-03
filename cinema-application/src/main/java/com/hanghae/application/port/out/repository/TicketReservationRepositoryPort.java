package com.hanghae.application.port.out.repository;

import com.hanghae.domain.model.TicketReservation;
import com.hanghae.domain.model.enums.ScreenSeat;

import java.util.List;

public interface TicketReservationRepositoryPort {
    List<TicketReservation> saveMovieReservations(List<TicketReservation> ticketReservations);
    int countByScreeningScheduleIdAndMemberId(Long scheduleId, Long memberId);
    int countByScheduleIdAndScreenSeats(Long scheduleId, List<ScreenSeat> screenSeats);
}

