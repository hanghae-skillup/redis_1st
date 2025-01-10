package com.redis.movies.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository: JpaRepository<Movie, Long> {

    fun findByReleaseDateOrderByDesc(): List<Movie>
}