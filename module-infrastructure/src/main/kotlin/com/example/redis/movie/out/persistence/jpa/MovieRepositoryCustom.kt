package com.example.redis.movie.out.persistence.jpa


interface MovieRepositoryCustom {
    fun findByOrderByReleaseDateDesc(title: String?, genre: String?): MutableList<MovieEntity>
}