package com.example.redis.movie.`in`.dto

import com.example.redis.movie.ScreeningSchedule
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ScreeningScheduleResponseDto(

    @JsonProperty(value = "start_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val startTime: LocalDateTime,

    @JsonProperty(value = "end_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val endTime: LocalDateTime,
) {
    companion object {
        fun toDto(screeningSchedule: ScreeningSchedule):ScreeningScheduleResponseDto {
            return ScreeningScheduleResponseDto(
                startTime = screeningSchedule.startTime,
                endTime = screeningSchedule.endTime,
            )
        }

    }
}