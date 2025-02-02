package com.example.movie.application.dto;

import java.util.List;

public record ScreeningsDetail(
        Long theaterId,
        String theater,
        List<ScreeningTimeDetail> screeningTimes
) {
}
