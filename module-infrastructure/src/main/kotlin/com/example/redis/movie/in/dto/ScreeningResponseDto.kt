package com.example.redis.movie.`in`.dto

import com.example.redis.movie.Screening
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ScreeningResponseDto(

    @JsonProperty(value = "theater_id")
    val theaterId: Long,

    @JsonProperty(value = "theater_name")
    val theaterName: String,

    @JsonProperty(value = "screening_id")
    val screeningId: Long,

    @JsonProperty(value = "start_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val startTime: LocalDateTime,

    @JsonProperty(value = "end_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val endTime: LocalDateTime,

) {
    companion object {
        fun toDto(screening: Screening): ScreeningResponseDto {
            return ScreeningResponseDto(
                theaterId = screening.theater.theaterId,
                theaterName = screening.theater.name,
                screeningId = screening.screeningId,
                startTime = screening.startTime,
                endTime = screening.endTime,
            )
        }
    }
}