package com.example.application.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

public record ScreeningRequestDto(

        @NotNull(message = "Theater must not be null")
        Long theaterId,

        @NotNull(message = "Start time must not be null")
        LocalTime startTime

        ) {}
