package com.example.application.dto.response;

import com.example.domain.model.entity.Reservation;
import java.time.LocalTime;
import java.util.List;

public record ReservationResponseDto(
        String memberName,
        String movieTitle,
        String theaterName,
        LocalTime screeningStartTime,
        List<String> reservedSeats
) {
    public static ReservationResponseDto fromEntity(Reservation reservation) {
        return new ReservationResponseDto(
                reservation.getMember().getName(),
                reservation.getScreening().getMovie().getTitle(),
                reservation.getScreening().getTheater().getName(),
                reservation.getScreening().getStartTime(),
                reservation.getReservedSeats().stream()
                        .map(rs -> rs.getScreeningSeat().getSeat().getSeatNumber().toString())
                        .toList()
        );
    }
}

