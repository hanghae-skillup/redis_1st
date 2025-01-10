package com.example.movie.persistence.genre.repository

import com.example.movie.persistence.genre.model.GenreEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GenreJpaRepository: JpaRepository<GenreEntity, Long> {
}