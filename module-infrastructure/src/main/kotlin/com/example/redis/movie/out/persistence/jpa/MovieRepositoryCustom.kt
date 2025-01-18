package com.example.redis.movie.out.persistence.jpa

import com.example.redis.movie.query.MovieProjection

interface MovieRepositoryCustom {
    fun findByOrderByReleaseDateDesc(title: String?, genre: String?): MutableList<MovieEntity>
}