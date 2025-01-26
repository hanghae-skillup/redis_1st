package com.example.entity.movie;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Genre {
    ACTION("액션"),
    SF("SF"),
    ROMANCE("로멘스"),
    HORROR("호로");

    private final String description;
}
