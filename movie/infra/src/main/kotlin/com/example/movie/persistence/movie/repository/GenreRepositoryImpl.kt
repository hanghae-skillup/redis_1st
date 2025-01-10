package com.example.movie.persistence.movie.repository

import com.example.movie.domain.movie.model.Genre
import com.example.movie.domain.movie.repository.GenreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class GenreRepositoryImpl(
    private val genreJpaRepository: com.example.movie.persistence.movie.repository.GenreJpaRepository
) : GenreRepository {
    override fun findById(id: Long): Genre? {
        return genreJpaRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun findByName(name: String): Genre? {
        return genreJpaRepository.findByName(name)?.toDomain()
    }

    override fun findAll(): List<Genre> {
        return genreJpaRepository.findAll().map { it.toDomain() }
    }
}