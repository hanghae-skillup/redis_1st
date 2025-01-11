package com.example.entity;

import lombok.Getter;

@Getter
public enum Genre {
    ACTION("액션"),
    SF("SF"),
    ROMANCE("로멘스"),
    HORROR("호로");

    private final String description;

    Genre(String description) {
        this.description = description;
    }
}