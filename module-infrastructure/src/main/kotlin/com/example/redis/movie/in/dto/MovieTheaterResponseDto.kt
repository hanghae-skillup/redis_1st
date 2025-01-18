package com.example.redis.movie.`in`.dto

import com.example.redis.movie.MovieTheater
import com.fasterxml.jackson.annotation.JsonProperty

data class MovieTheaterResponseDto(

    @JsonProperty(value = "theater_name")
    val theaterName: String,

    @JsonProperty(value = "screening_schedules")
    val screeningSchedules: MutableList<ScreeningScheduleResponseDto> = mutableListOf()
) {

    companion object {
        fun toDto(movieTheater: MovieTheater): MovieTheaterResponseDto {
            return MovieTheaterResponseDto(
                theaterName = movieTheater.name,
                screeningSchedules = movieTheater.screeningSchedules.stream().map { ScreeningScheduleResponseDto.toDto(it) }.toList(),
            )
        }
    }
}