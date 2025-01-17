package com.example.movie.domain.screening.model

import com.example.movie.domain.common.model.BaseModel
import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.theater.model.Theater
import java.time.LocalDateTime

class Screening(
    val id: Long,
    val movieId: Long,
    val theater: Theater,
    val screeningTime: LocalDateTime,
    val screeningEndTime: LocalDateTime,
    val status: ScreeningStatus,
    override val createdBy: String,
    override val createdAt: LocalDateTime,
    override val updatedBy: String,
    override val updatedAt: LocalDateTime
) : BaseModel