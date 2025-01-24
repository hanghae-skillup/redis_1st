package com.movie.movieapi.interfaces.movie.dto;

import com.movie.domain.movie.dto.command.ReservationCommand;

import java.util.List;

public class ReservationDto {

    public record Reserve(Long scheduleId, List<Long> seatIds) {
        public static Reserve of(Long scheduleId, List<Long> seatIds) {
            return new Reserve(scheduleId, seatIds);
        }

        public ReservationCommand.Reserve toCommand(String token) {
            return ReservationCommand.Reserve.of(scheduleId, seatIds, token);
        }
    }

}
