package com.example.redis.movie

import com.example.redis.theater.Theater
import java.time.LocalDateTime

data class Movie(
    val movieId: Long,
    val title: String,
    val runningTime: Long,
    val releaseDate: LocalDateTime,
    val thumbnailImagePath: String,
    val filmRatings: String,
    val movieGenre: MutableList<String> = mutableListOf(),
    val screenings: MutableList<Screening> = mutableListOf(),
    val createAt: LocalDateTime?,
    val updateAt: LocalDateTime?,
    ) {

    init {
        this.screenings.sortBy { it.startTime }
    }
}