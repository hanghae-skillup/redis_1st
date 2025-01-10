package com.example.movie.response;

import java.util.List;

public record ScreeningsDetail (
        String theater,
        List<String>screeningTime
) {
}
