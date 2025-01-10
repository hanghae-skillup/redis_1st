package com.movie.moviedomain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilmRating {

    R_ALL("전체관뢈가", 0),
    R_12("12세 관람가", 10),
    R_15("15세 관람가", 20),
    R_19("19세 관람가", 30),
    R_RESTRICTED("제한 관람가", 40),

    ;

    private final String kor;
    private final int value;

}
