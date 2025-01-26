package com.example.application.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ServiceReservationResponseDto {
    private final String theaterName;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final List<String> reservedSeats;
}
