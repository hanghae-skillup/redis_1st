package com.redis.port

import com.redis.domain.Cinema

interface CinemaPort {
    fun getCinema(cinemaId: Long): Cinema
    fun getCinemas(cinemaIds: MutableList<Long>): MutableList<Cinema>
}