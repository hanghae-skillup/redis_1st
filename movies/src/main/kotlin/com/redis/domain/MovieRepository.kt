package com.redis.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository: JpaRepository<Movie, Long> {

    fun findByOrderByReleaseDateDesc(): List<Movie>
}