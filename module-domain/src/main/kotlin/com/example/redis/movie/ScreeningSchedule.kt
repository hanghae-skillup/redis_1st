package com.example.redis.movie

import java.time.LocalDateTime

data class ScreeningSchedule(
    val screeningScheduleId: Long,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
) {
}