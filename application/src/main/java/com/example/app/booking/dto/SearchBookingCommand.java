package com.example.app.booking.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SearchBookingCommand(
        Long userId,
        Long movieId,
        Long showtimeId,
        Long theaterId,
        LocalDate bookingDate
){
}
