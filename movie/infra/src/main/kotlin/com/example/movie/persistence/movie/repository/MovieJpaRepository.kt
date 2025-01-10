package com.example.movie.persistence.movie.repository

import com.example.movie.persistence.movie.entity.MovieEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieJpaRepository : JpaRepository<MovieEntity, Long> {
    fun findAllByOrderByReleaseDateDesc(): List<MovieEntity>
}