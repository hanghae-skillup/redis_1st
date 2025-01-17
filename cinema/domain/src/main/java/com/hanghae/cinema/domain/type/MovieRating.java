package com.hanghae.cinema.domain.type;

public enum MovieRating {
    ALL("전체 관람가"),
    AGE_12("12세 관람가"),
    AGE_15("15세 관람가"),
    RESTRICTED("청소년 관람 불가");

    private String description;

    MovieRating(String description) {
        this.description = description;
    }
}
