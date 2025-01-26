package com.example.movie.persistence.screening.repository

import com.example.movie.persistence.screening.model.ScreeningEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScreeningJpaRepository : JpaRepository<ScreeningEntity, Long> {
}