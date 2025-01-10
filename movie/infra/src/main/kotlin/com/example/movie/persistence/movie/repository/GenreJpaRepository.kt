package com.example.movie.persistence.movie.repository

import com.example.movie.persistence.movie.entity.GenreEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GenreJpaRepository: JpaRepository<GenreEntity, Long> {
    fun findByName(name: String): GenreEntity?
}