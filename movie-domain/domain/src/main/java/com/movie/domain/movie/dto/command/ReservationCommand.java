package com.movie.domain.movie.dto.command;

import java.util.List;

public class ReservationCommand {

    public record Reserve(Long scheduleId, List<Long> seatIds, String token) {
        public static Reserve of(Long scheduleId, List<Long> seatIds, String token) {
            return new Reserve(scheduleId, seatIds, token);
        }
    }

}
