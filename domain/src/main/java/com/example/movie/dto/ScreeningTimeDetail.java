package com.example.movie.dto;

import java.time.LocalDateTime;

public record ScreeningTimeDetail(
        LocalDateTime startAt,
        LocalDateTime endAt
) {
}
