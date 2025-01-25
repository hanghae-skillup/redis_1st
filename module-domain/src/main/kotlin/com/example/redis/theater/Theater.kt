package com.example.redis.theater

import com.example.redis.movie.Screening

data class Theater(
    val theaterId: Long,
    val name: String,
    val seats: MutableList<Seat> = mutableListOf()
) {
}