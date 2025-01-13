package com.example.app.movie;

import lombok.Builder;

import java.time.LocalTime;

@Builder
public record Showtime (LocalTime start, LocalTime end) {
}
