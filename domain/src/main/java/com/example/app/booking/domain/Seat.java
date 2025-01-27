package com.example.app.booking.domain;

import com.example.app.movie.type.TheaterSeat;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record Seat(
        Long id,
        Long bookingId,
        Long movieId,
        Long showtimeId,
        Long theaterId,
        LocalDate bookingDate,
        TheaterSeat theaterSeat,
        boolean reserved
) {
}
