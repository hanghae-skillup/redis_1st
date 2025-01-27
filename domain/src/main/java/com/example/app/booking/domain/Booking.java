package com.example.app.booking.domain;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record Booking(
        Long id,
        Long userId,
        Long movieId,
        Long showtimeId,
        Long theaterId,
        Integer totalSeats,
        LocalDate bookingDate
){
}
