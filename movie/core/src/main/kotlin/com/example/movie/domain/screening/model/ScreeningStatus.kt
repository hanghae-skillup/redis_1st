package com.example.movie.domain.screening.model

enum class ScreeningStatus(
    val description: String
) {
    CANCELLED("상영 취소"),
    SCHEDULED("상영 전"),
    IN_PROGRESS("상영 중"),
    COMPLETED("상영 완료")
}