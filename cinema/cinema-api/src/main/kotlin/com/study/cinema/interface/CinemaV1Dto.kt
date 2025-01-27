package com.study.cinema.`interface`

import com.study.cinema.domain.movie.Genre
import com.study.cinema.domain.movie.MovieInfo
import java.time.LocalDate
import java.time.ZonedDateTime

class CinemaV1Dto {
    class Response {
        data class MovieWithTheaterSchedule(
            val movieId: Long,
            val movieTitle: String,
            val movieRating: MovieRating,
            val releaseDate: LocalDate,
            val thumbnailUrl: String,
            val runningTimeMinutes: Int,
            val genre: Genre,
            val schedules: List<ScheduleArea>
        ) {
            constructor(movieSchedule: MovieInfo.MovieSchedule) : this(
                movieId = movieSchedule.movieId,
                movieTitle = movieSchedule.movieTitle,
                movieRating = MovieRating.valueOf(movieSchedule.movieRating.name),
                releaseDate = movieSchedule.releaseDate,
                thumbnailUrl = movieSchedule.thumbnailUrl,
                runningTimeMinutes = movieSchedule.runningTimeMinutes,
                genre = movieSchedule.genre,
                movieSchedule.schedules.map { ScheduleArea(it) }
            )
        }

        data class ScheduleArea(
            val movieScheduleId: Long,
            val theaterTitle: String,
            val startAt: ZonedDateTime,
            val endAt: ZonedDateTime,
        ) {
            constructor(theaterSchedule: MovieInfo.TheaterScheduleArea) : this(
                movieScheduleId = theaterSchedule.movieScheduleId,
                theaterTitle = theaterSchedule.theaterTitle,
                startAt = theaterSchedule.startAt,
                endAt = theaterSchedule.endAt,
            )
        }
    }

    enum class MovieRating(val description: String) {
        G("전체 관람가"),
        PG_13("12세 이상 관람가"),
        PG_15("15세 이상 관람가"),
        R("청소년 관람불가"),
        X("제한 상영가")
    }
}