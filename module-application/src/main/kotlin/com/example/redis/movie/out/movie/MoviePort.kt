package com.example.redis.movie.out.movie

import com.example.redis.movie.Movie
import com.example.redis.movie.Reservation

interface MoviePort {
    fun findByOrderByReleaseDateDesc(title: String?, genre: String?): MutableList<Movie>
    fun reserve(reservation: Reservation): String
}