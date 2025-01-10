package com.example.entity;

public enum Rating {
    ALL("전체 관람가"),
    TWELVE("12세 관람가"),
    FIFTEEN("15세 관람가"),
    ADULT("청소년 관람 불가");

    private String description;

    Rating(String description) {
        this.description = description;
    }
}
