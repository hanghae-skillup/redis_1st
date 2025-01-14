package com.example.redis.movie.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository: JpaRepository<MovieEntity, Long> {

    fun findByOrderByReleaseDateDesc(): MutableList<MovieEntity>
}