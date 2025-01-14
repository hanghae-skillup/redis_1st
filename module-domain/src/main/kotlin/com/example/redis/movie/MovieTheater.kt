package com.example.redis.movie

data class MovieTheater(
    val theaterId: Long,
    val name: String,
    val screeningSchedules: MutableList<ScreeningSchedule> = mutableListOf()
) {
}