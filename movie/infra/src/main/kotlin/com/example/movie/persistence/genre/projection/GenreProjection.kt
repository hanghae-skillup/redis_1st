package com.example.movie.persistence.genre.projection

import com.example.movie.domain.movie.model.Genre
import java.time.LocalDateTime

interface GenreProjection {
    val id: Long
    val name: String
}