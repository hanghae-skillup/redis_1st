package com.redis.cinema.port

import com.redis.cinema.domain.Cinema

interface CinemaPort {
    fun getCinema(cinemaId: Long): Cinema
    fun getCinemas(cinemaIds: MutableList<Long>): MutableList<Cinema>
}