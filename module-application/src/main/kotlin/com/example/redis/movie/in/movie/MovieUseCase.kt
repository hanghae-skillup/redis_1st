package com.example.redis.movie.`in`.movie

import com.example.redis.movie.command.Movie
import com.example.redis.movie.query.MovieProjection

interface MovieUseCase {

    fun gets(title: String?, genre: String?): MutableList<MovieProjection>
}