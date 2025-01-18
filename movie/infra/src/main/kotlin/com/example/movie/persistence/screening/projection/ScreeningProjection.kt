package com.example.movie.persistence.screening.projection

import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.persistence.theater.projection.TheaterProjection
import java.time.LocalDateTime

interface ScreeningProjection {
    val id: Long
    val screeningTime: LocalDateTime
    val screeningEndTime: LocalDateTime
    val status: ScreeningStatus
    val theater: TheaterProjection
}