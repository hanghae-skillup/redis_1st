package com.example.movie.persistence.theater.repository

import com.example.movie.domain.theater.model.Theater
import com.example.movie.domain.theater.repository.TheaterRepository
import org.springframework.stereotype.Repository

@Repository
class TheaterRepositoryImpl(
    private val theaterJpaRepository: TheaterJpaRepository
) : TheaterRepository {
    override fun findById(id: Long): Theater? {
        return theaterJpaRepository.findById(id).orElse(null)?.toDomain()
    }

    override fun findAll(): List<Theater> {
        return theaterJpaRepository.findAll().map { it.toDomain() }
    }
}