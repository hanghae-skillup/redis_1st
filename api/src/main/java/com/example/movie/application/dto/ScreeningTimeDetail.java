package com.example.movie.application.dto;

import java.time.LocalDateTime;

public record ScreeningTimeDetail(
        LocalDateTime startAt,
        LocalDateTime endAt
) {
}
