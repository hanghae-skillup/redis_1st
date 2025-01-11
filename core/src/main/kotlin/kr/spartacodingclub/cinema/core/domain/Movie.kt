package kr.spartacodingclub.cinema.core.domain

import java.time.LocalDate

data class Movie(
    val id: Long,
    val title: String,
    val rating: MovieRating,
    val releaseDate: LocalDate,
    val thumbnailUrl: String,
    val runningTime: Int,
    val genre: Genre
)
