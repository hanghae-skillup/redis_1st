package com.example.redis.movie.`in`.dto

import com.example.redis.movie.Movie
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class MovieResponseDto(

    @JsonProperty(value = "id")
    val id: Long,

    @JsonProperty(value = "title")
    val title: String,

    @JsonProperty(value = "film_ratings")
    val filmRatings: String,

    @JsonProperty(value = "thumbnail_image_path")
    val thumbnailImagePath: String,

    @JsonProperty(value = "running_time")
    val runningTime: Long,

    @JsonProperty(value = "release_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val releaseDate: LocalDateTime,

    @JsonProperty(value = "movie_genre")
    val movieGenre: String,

    @JsonProperty(value = "theater")
    val theater: MutableList<MovieTheaterResponseDto> = mutableListOf(),

    ) {
    companion object {
        fun toDto(movie: Movie): MovieResponseDto {
            return MovieResponseDto(
                id = movie.movieId,
                title = movie.title,
                filmRatings = movie.filmRatings,
                runningTime = movie.runningTime,
                thumbnailImagePath = movie.thumbnailImagePath,
                releaseDate = movie.releaseDate,
                movieGenre = movie.movieGenre,
                theater = movie.theaters.stream().map { MovieTheaterResponseDto.toDto(it) }.toList()
            )
        }

    }
}