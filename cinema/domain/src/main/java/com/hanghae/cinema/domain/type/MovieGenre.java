package com.hanghae.cinema.domain.type;


public enum MovieGenre {
    ACTION("액션"),
    ROMANCE("로맨스"),
    HORROR("호러"),
    SF("SF");

    private final String name;

    MovieGenre(String name) {
        this.name = name;
    }
}
