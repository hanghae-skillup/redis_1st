package com.redis.movies.controller.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.redis.movies.domain.ScreeningSchedule
import java.time.LocalDateTime

data class ScreeningScheduleResponse(

    @JsonProperty(value = "start_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private val startTime: LocalDateTime,

    @JsonProperty(value = "end_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private val endTime: LocalDateTime,
) {
    companion object {
        fun fromEntity(screeningSchedule: ScreeningSchedule): ScreeningScheduleResponse {
            return ScreeningScheduleResponse(
                startTime = screeningSchedule.startTime,
                endTime = screeningSchedule.endTime

            )
        }

    }

}