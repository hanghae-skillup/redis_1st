package com.example.movie.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScreeningInfoResponse {
    private final String theaterName;
    private final String startedAt;
    private final String endedAt;
}
