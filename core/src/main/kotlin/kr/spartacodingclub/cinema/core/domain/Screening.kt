package kr.spartacodingclub.cinema.core.domain

import java.time.LocalDateTime

data class Screening(
    val id: Long,
    val movieId: Long,
    val theater: Theater,
    val startTime: LocalDateTime
)
