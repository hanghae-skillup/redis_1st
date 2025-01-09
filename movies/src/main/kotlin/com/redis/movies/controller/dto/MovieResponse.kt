package com.redis.movies.controller.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.redis.movies.domain.Movie
import com.redis.movies.domain.MovieGenre
import com.redis.movies.domain.ScreeningSchedules
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

data class MovieResponse(

    @JsonProperty(value = "id")
    private val id: Long,

    @JsonProperty(value = "title")
    private val title: String,

    @JsonProperty(value = "film_ratings")
    private val filmRatings: String,

    @JsonProperty(value = "release_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private val releaseDate: LocalDateTime,

    @JsonProperty(value = "thumbnail_image_path")
    private val thumbnailImagePath: String,

    @JsonProperty(value = "running_time")
    private val runningTime: Long,

    @JsonProperty(value = "genre")
    private val genre: String,

    @JsonProperty(value = "theater_name")
    private val theaterName: String,

    @JsonProperty(value = "screening_schedules")
    private val screeningSchedules: MutableList<ScreeningScheduleResponse>
) {
    companion object {
        fun fromEntity(movie: Movie): MovieResponse {
            requireNotNull(movie.id) {}
            return MovieResponse(
                id = movie.id,
                title = movie.title,
                filmRatings = movie.filmRatings,
                releaseDate = movie.releaseDate,
                thumbnailImagePath = movie.thumbnailImagePath,
                runningTime = movie.runningTime,
                genre= movie.genre.name,
                theaterName = movie.theaterName,
                screeningSchedules = movie.screeningSchedules.getsStartTimeOrderByDesc().map { ScreeningScheduleResponse.fromEntity(it) }.toMutableList()
            )
        }
    }

}