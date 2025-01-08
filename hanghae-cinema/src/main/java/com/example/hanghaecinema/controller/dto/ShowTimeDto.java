package com.example.hanghaecinema.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ShowTimeDto(
        LocalDate showDate,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
