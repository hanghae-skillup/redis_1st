package com.example.repository.movie.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScreeningInfoDto {
    private final Long movieId;
    private final Long screeningId;
    private final String theaterName;
    private final LocalDateTime startedAt;
    private final LocalDateTime endedAt;

    @QueryProjection
    public ScreeningInfoDto(Long movieId, Long screeningId, String theaterName, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.movieId = movieId;
        this.screeningId = screeningId;
        this.theaterName = theaterName;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }
}
