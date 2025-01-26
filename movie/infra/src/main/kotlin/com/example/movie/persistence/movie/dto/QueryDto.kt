package com.example.movie.persistence.movie.dto

import com.example.movie.domain.movie.model.Rating
import com.example.movie.domain.screening.model.ScreeningStatus
import java.time.LocalDate
import java.time.LocalDateTime

data class MovieWithGenreDto(
    val id: Long,
    val title: String,
    val rating: Rating,
    val releaseDate: LocalDate,
    val thumbnailUrl: String,
    val runningTime: Int,
    val genreId: Long,
    val genreName: String
)

data class ScreeningWithTheaterDto(
    val id: Long,
    val movieId: Long,
    val screeningTime: LocalDateTime,
    val screeningEndTime: LocalDateTime,
    val status: ScreeningStatus,
    val theaterId: Long,
    val theaterName: String
)