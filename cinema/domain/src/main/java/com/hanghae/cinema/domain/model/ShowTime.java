package com.hanghae.cinema.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ShowTime {
    private Long id;
    private Movie movie;
    private Theater theater;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
