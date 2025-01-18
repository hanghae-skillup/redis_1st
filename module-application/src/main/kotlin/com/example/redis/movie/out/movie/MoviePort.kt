package com.example.redis.movie.out.movie

import com.example.redis.movie.command.Movie
import com.example.redis.movie.query.MovieProjection

interface MoviePort {
    fun findByOrderByReleaseDateDesc(title: String?, genre: String?): MutableList<MovieProjection>
}