package com.study.cinema.domain.movie

import com.querydsl.core.annotations.QueryProjection
import java.io.Serializable
import java.time.LocalDate
import java.time.ZonedDateTime

class MovieInfo {

    data class MovieSchedule @QueryProjection constructor (
        val movieId: Long,
        val movieTitle: String,
        val movieRating: MovieRating,
        val releaseDate: LocalDate,
        val thumbnailUrl: String,
        val runningTimeMinutes: Int,
        val genre: Genre,
        val schedules: List<TheaterScheduleArea>
    ) : Serializable

    data class TheaterScheduleArea @QueryProjection constructor (
        val movieScheduleId: Long,
        val theaterTitle: String,
        val startAt: ZonedDateTime,
        val endAt: ZonedDateTime,
    ) : Serializable
}