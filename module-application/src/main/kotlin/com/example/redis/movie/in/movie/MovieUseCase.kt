package com.example.redis.movie.`in`.movie

import com.example.redis.movie.Movie

interface MovieUseCase {

    fun gets(title: String?, genre: String?): MutableList<Movie>
}