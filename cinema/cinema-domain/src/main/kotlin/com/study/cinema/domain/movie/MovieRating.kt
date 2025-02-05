package com.study.cinema.domain.movie

enum class MovieRating(val description: String) {
    G("전체 관람가"),
    PG_13("12세 이상 관람가"),
    PG_15("15세 이상 관람가"),
    R("청소년 관람불가"),
    X("제한 상영가")
}