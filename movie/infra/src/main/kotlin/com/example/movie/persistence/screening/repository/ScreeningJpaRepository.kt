package com.example.movie.persistence.screening.repository

import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.persistence.screening.model.ScreeningEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ScreeningJpaRepository : JpaRepository<ScreeningEntity, Long> {
    @Query("""
        SELECT s 
        FROM ScreeningEntity s
        JOIN FETCH s.theater t
        WHERE s.movieId = :movieId
        AND s.screeningTime > :currentTime 
        AND s.status = :status
        ORDER BY s.screeningTime ASC
    """)
    fun findCurrentScreeningsByMovieIdAndStatus(
        @Param("movieId") movieId: Long,
        @Param("currentTime") currentTime: LocalDateTime,
        @Param("status") status: ScreeningStatus
    ): List<ScreeningEntity>
}