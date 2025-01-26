package com.example.app.movie.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MovieRating {
    ALL_AGES("전체관람가"),
    TWELVE_ABOVE("12세 이상 관람가"),
    FIFTEEN_ABOVE("15세 이상 관람가"),
    NO_MINORS("청소년 관람불가"),
    RESTRICTED("제한상영가");

    private final String description;
}
