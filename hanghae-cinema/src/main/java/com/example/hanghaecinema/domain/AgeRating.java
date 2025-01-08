package com.example.hanghaecinema.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AgeRating {
    G("전체관람가"),
    PG_13("12세 이상 관람가"),
    R("15세 이상 관람가"),
    NC_17("청소년 관람불가"),
    REJECT("제한상영가");

    private final String displayName;

}
