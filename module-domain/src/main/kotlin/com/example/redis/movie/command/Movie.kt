package com.example.redis.movie.command

import java.time.LocalDateTime

data class Movie(
    val movieId: Long,
    val title: String,
    val runningTime: Long,
    val releaseDate: LocalDateTime,
    val thumbnailImagePath: String,
    val filmRatings: String,
    val movieGenre: MutableList<String> = mutableListOf(),
    val theaters: MutableList<MovieTheater> = mutableListOf(),
    val createAt: LocalDateTime?,
    val updateAt: LocalDateTime?,

    ) {
}