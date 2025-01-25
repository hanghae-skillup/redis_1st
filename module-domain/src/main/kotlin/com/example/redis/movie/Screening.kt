package com.example.redis.movie

import com.example.redis.theater.Theater
import java.time.LocalDateTime

data class Screening(
    val screeningScheduleId: Long,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val theater: Theater,
) {
}