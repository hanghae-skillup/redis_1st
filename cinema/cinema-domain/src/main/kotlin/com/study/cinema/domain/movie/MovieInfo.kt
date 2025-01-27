package com.study.cinema.domain.movie

import java.time.LocalDate
import java.time.ZonedDateTime

class MovieInfo {

    data class MovieSchedule (
        val movieId: Long,
        val movieTitle: String,
        val movieRating: MovieRating,
        val releaseDate: LocalDate,
        val thumbnailUrl: String,
        val runningTimeMinutes: Int,
        val genre: Genre,
        val schedules: List<TheaterScheduleArea>
    ) {
        constructor(
            movie: Movie,
            movieSchedules: List<com.study.cinema.domain.schedule.MovieSchedule>
        ) : this(
            movieId = movie.id,
            movieTitle = movie.title,
            movieRating = movie.movieRating,
            releaseDate = movie.releaseDate,
            thumbnailUrl = movie.thumbnailUrl,
            runningTimeMinutes = movie.runningTimeMinutes,
            genre = movie.genre,
            schedules = movieSchedules.map { TheaterScheduleArea(it) }
        )
    }

    data class TheaterScheduleArea (
        val movieScheduleId: Long,
        val theaterTitle: String,
        val startAt: ZonedDateTime,
        val endAt: ZonedDateTime,
    ) {
        constructor(movieSchedule: com.study.cinema.domain.schedule.MovieSchedule): this(
            movieScheduleId = movieSchedule.id,
            theaterTitle = movieSchedule.theater.title,
            startAt = movieSchedule.startAt,
            endAt = movieSchedule.endAt,
        )
    }



}