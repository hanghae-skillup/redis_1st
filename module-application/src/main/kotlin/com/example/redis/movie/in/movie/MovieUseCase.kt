package com.example.redis.movie.`in`

import com.example.redis.movie.Movie

interface MovieUseCase {

    fun gets(): MutableList<Movie>
}