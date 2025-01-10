package com.redis.controller.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.redis.domain.Cinema
import com.redis.domain.Movie
import java.time.LocalDateTime

data class MovieResponse(

    @JsonProperty(value = "id")
    val id: Long,

    @JsonProperty(value = "title")
    val title: String,

    @JsonProperty(value = "film_ratings")
    val filmRatings: String,

    @JsonProperty(value = "release_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val releaseDate: LocalDateTime,

    @JsonProperty(value = "thumbnail_image_path")
    val thumbnailImagePath: String,

    @JsonProperty(value = "running_time")
    val runningTime: Long,

    @JsonProperty(value = "genre")
    val genre: String,

    @JsonProperty(value = "cinema_id")
    val cinemaId: Long?,

    @JsonProperty(value = "cinema_name")
    val cinemaName: String,

    @JsonProperty(value = "screening_schedules")
    val screeningSchedules: MutableList<ScreeningScheduleResponse>
) {
    companion object {
        fun fromEntity(movie: Movie, cinema: Cinema): MovieResponse {
            requireNotNull(movie.id) {}
            return MovieResponse(
                id = movie.id,
                title = movie.title,
                filmRatings = movie.filmRatings.description,
                releaseDate = movie.releaseDate,
                thumbnailImagePath = movie.thumbnailImagePath,
                runningTime = movie.runningTime,
                genre= movie.genre.name,
                cinemaId = cinema.id,
                cinemaName = cinema.name,
                screeningSchedules = movie.screeningSchedules.getsStartTimeOrderByDesc().map { ScreeningScheduleResponse.fromEntity(it) }.toMutableList()
            )
        }
    }

}