package com.example.app.movie.domain;

import lombok.Builder;

import java.time.LocalTime;

@Builder
public record Showtime (LocalTime start, LocalTime end) {
}
