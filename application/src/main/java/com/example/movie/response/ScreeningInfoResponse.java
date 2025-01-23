package com.example.movie.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class ScreeningInfoResponse implements Serializable {
    private final String theaterName;
    private final String startedAt;
    private final String endedAt;
}
