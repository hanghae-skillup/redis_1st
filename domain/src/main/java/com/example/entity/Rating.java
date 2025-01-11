package com.example.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Rating {
    ALL("전체 관람가"),
    TWELVE("12세 관람가"),
    FIFTEEN("15세 관람가"),
    ADULT("청소년 관람 불가");

    private final String description;
}
