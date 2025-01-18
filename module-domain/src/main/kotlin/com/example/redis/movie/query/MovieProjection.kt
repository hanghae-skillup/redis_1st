package com.example.redis.movie.query

import com.example.redis.movie.FilmRatings
import com.example.redis.movie.MovieTheater
import java.time.LocalDateTime

class MovieProjection(
    val movieId: Long,
    val title: String,
    val runningTime: Long,
    val releaseDate: LocalDateTime,
    val thumbnailImagePath: String,
    val filmRatings: FilmRatings,
    val movieGenre: MutableList<String> = mutableListOf(),
    val theaters: MutableList<MovieTheater> = mutableListOf(),
    val createAt: LocalDateTime?,
    val updateAt: LocalDateTime?,
    ) {
}