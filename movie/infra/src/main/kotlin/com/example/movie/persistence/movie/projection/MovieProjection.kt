package com.example.movie.persistence.movie.projection

import com.example.movie.domain.movie.model.Rating
import com.example.movie.persistence.genre.projection.GenreProjection
import com.example.movie.persistence.screening.projection.ScreeningProjection
import java.time.LocalDate

interface MovieProjection {
    val id: Long
    val title: String
    val rating: Rating
    val releaseDate: LocalDate
    val thumbnailUrl: String
    val runningTime: Int
    val genre: GenreProjection
    val screenings: List<ScreeningProjection>
}