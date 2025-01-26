package com.movie.storage.movie.dto.statement;

import java.util.List;

public class ReservationStatement {

    public record Get(Long scheduleId, List<Long> seatIds) {
        public static Get of(Long scheduleId, List<Long> seatIds) {
            return new Get(scheduleId, seatIds);
        }
    }

}
