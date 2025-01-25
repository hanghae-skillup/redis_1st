package com.example.app.booking.dto;

import com.example.app.movie.type.TheaterSeat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record SearchSeatCommand(
        Long bookingId,
        Long movieId,
        Long showtimeId,
        Long theaterId,
        LocalDate bookingDate,
        Set<TheaterSeat> seats
) {
}
