package com.hanghae.domain.service;

import com.hanghae.domain.model.Member;
import com.hanghae.domain.model.ScreeningSchedule;
import com.hanghae.domain.model.TicketReservation;
import com.hanghae.domain.model.enums.ScreenSeat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    public List<TicketReservation> generateTicketReservationsBySeat(ScreeningSchedule screeningSchedule, Member member, ScreenSeat screenSeat, int seatCount) {
        List<ScreenSeat> selectedSeats = ScreenSeat.getSelectedConnectedSeats(screenSeat, seatCount);

        return selectedSeats.stream()
                .map(seat -> new TicketReservation(
                        null,
                        seat,
                        screeningSchedule,
                        member, member.getMemberId(),
                        null,
                        null,
                        null
                ))
                .collect(Collectors.toList());
    }
}
