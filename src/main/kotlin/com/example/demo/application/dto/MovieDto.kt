package com.example.demo.application.dto

import java.time.LocalDateTime

data class MovieDto(
    val title: String,
    val rating: String,
    val releaseDate: LocalDateTime,
    val thumbnailUrl: String,
    val runningTime: Int,
    val genre: String,
    val screenings: List<ScreeningDto>
)

data class ScreeningDto(
    val cinemaName: String,
    val startTime: LocalDateTime
) 