package org.movie.moviedomain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genre {
    ACTION("액션"),
    COMEDY("코미디"),
    DRAMA("드라마"),
    HORROR("공포"),
    ROMANCE("로맨스"),
    ;

    private final String text;
}
