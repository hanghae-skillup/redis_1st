package com.example.app.movie.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MovieGenre {
    ACTION("액션"),
    COMEDY("코미디"),
    FAMILY("가족"),
    ROMANCE("로맨스"),
    HORROR("호러"),
    SF("SF");

    private final String description;
}
