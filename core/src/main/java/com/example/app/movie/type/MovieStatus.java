package com.example.app.movie.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MovieStatus {
    SCHEDULED("상용예정"),
    SHOWING("상용중"),
    ENDED("상용종료");

    private final String description;
}
