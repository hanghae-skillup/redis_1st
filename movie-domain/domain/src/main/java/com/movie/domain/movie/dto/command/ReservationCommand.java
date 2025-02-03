package com.movie.domain.movie.dto.command;

import java.time.LocalDateTime;
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

    public record CreateCache(Long scheduleId, Long userId, LocalDateTime reservedAt) {
        public static CreateCache of(Long scheduleId, Long userId, LocalDateTime reservedAt) {
            return new CreateCache(scheduleId, userId, reservedAt);
        }
    }

    public record GetCache(Long scheduleId, Long userId) {
        public static GetCache of(Long scheduleId, Long userId) {
            return new GetCache(scheduleId, userId);
        }
    }

}
