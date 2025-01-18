package com.example.redis.movie.command

import java.time.LocalDateTime

data class ScreeningSchedule(
    val screeningScheduleId: Long,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
) {
}