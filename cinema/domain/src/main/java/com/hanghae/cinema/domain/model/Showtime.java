package com.hanghae.cinema.domain.model;

import java.time.LocalDateTime;

public class Showtime {
    private Long id;
    private Movie movie;
    private Theater theater;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
