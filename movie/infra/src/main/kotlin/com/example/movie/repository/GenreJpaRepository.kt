package com.example.movie.repository

import com.example.movie.entity.GenreEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GenreJpaRepository: JpaRepository<GenreEntity, Long> {
    fun findByName(name: String): GenreEntity?
}