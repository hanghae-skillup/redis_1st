package com.example.movie.domain.movie.model

import com.example.movie.domain.common.model.BaseModel
import com.example.movie.domain.genre.model.Genre
import com.example.movie.domain.screening.model.Screening
import java.time.LocalDate
import java.time.LocalDateTime

class Movie(
    val id: Long,
    val title: String,
    val rating: Rating,
    val genre: Genre,
    val releaseDate: LocalDate,
    val thumbnailUrl: String,
    val runningTime: Int,
    val screenings: List<Screening>,
    override val createdBy: String,
    override val createdAt: LocalDateTime,
    override val updatedBy: String,
    override val updatedAt: LocalDateTime
) : BaseModel