package com.example.hanghaecinema.controller.dto;

import java.time.LocalDate;
import java.util.List;

public record MovieResponseDto(
        String title,
        String ageRating,
        LocalDate releaseDate,
        String thumbnailUrl,
        Integer runtimeMinutes,
        String genre,
        String theaterName,
        List<ShowTimeDto> showTimes
) {
}
