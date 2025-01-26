package com.movie.domain.movie.dto.command;

import java.util.List;

public class ReservationCommand {

    public record Get(Long schedule, List<Long> seatIds) {
        public static Get of(Long schedule, List<Long> seatIds) {
            return new Get(schedule, seatIds);
        }
    }

    public record GetReserveData(Long scheduleId, List<Long> seatIds, String token) {
        public static GetReserveData of(Long scheduleId, List<Long> seatIds, String token) {
            return new GetReserveData(scheduleId, seatIds, token);
        }
    }

    public record Reserve(Long scheduleId, List<Long> seatIds, Long userId) {
        public static Reserve of(Long scheduleId, List<Long> seatIds, Long userId) {
            return new Reserve(scheduleId, seatIds, userId);
        }
    }

}
