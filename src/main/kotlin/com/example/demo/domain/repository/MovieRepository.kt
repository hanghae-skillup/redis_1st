package com.example.demo.domain.repository

import com.example.demo.domain.entity.Movie
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository : JpaRepository<Movie, Long> {
    fun findByTitleContainingIgnoreCaseAndGenreContainingIgnoreCase(
        title: String?,
        genre: String?
    ): List<Movie>
} 