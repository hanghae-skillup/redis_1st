package com.example.redis.movie.`in`.movie

import com.example.redis.movie.Movie
import com.example.redis.movie.Reservation

interface MovieUseCase {

    fun gets(title: String?, genre: String?): MutableList<Movie>
    fun reserve(movieId: Long, reserve: Reservation): String
}