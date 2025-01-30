package com.example.redis.movie.out.movie

import com.example.redis.movie.Movie

interface MoviePort {
    fun findByOrderByReleaseDateDesc(title: String?, genre: String?): MutableList<Movie>
}