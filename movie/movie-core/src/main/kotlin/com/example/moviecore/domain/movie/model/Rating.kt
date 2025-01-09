package com.example.moviecore.domain.movie.model

enum class Rating(
    val description: String
) {
    ALL("전체 관람가"),
    TWELVE("12세 이상 관람가"),
    FIFTEEN("15세 이상 관람가"),
    ADULT("청소년 관람불가"),
    RESTRICTED("제한상영가")
}