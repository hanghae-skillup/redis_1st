package com.hanghae.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MovieRating {
    ALL("전체이용가"),
    TWELVE("12세"),
    FIFTEEN("15세"),
    ADULT("청소년 관람불가");

    private final String codeName;  // 사용자에게 보여줄 값
}
