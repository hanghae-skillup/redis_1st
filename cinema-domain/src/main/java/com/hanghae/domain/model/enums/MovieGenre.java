package com.hanghae.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MovieGenre {
    FAMILY("가족"),
    ACTION("액션"),
    THRILLER("스릴러"),
    ROMANCE("로맨스"),
    COMEDY("코메디");

    private final String codeName;  // 사용자에게 보여줄 값
}
