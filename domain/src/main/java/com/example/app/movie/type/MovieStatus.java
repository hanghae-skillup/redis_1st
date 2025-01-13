package com.example.app.movie.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MovieStatus {
    SCHEDULED("상영예정"),
    SHOWING("상영중"),
    ENDED("상영종료");

    private final String description;
}
