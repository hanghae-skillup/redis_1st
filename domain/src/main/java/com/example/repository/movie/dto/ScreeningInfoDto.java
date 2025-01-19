package com.example.repository.movie.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScreeningInfoDto {
    private final Long movieId;
    private final String theaterName;
    private final LocalDateTime startedAt;
    private final LocalDateTime endedAt;

    @QueryProjection
    public ScreeningInfoDto(Long movieId, String theaterName, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.movieId = movieId;
        this.theaterName = theaterName;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }
}
