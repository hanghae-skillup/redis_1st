package com.example.movie.dto;

import java.util.List;

public record ScreeningsDetail(
        Long theaterId,
        String theater,
        List<ScreeningTimeDetail> screeningTimes
) {
}
