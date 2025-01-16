package org.movie.moviedomain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rating {

    ALL("전체관람가"),
    TWELVE("12세 이상 관람가"),
    FIFTEEN("15세 이상 관람가"),
    ADULT("청소년 관람불가"),
    RESTRICTED("제한상영가"),
    ;

    private final String text;
}
