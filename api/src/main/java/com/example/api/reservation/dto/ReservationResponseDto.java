package com.example.api.reservation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ReservationResponseDto {
    private final String theaterName;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final List<String> reservedSeats;
}
