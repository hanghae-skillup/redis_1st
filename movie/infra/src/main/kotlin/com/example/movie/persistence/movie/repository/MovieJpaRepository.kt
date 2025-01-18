package com.example.movie.persistence.movie.repository

import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.persistence.movie.model.MovieEntity
import com.example.movie.persistence.movie.projection.MovieProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface MovieJpaRepository : JpaRepository<MovieEntity, Long> {
    fun findAllByOrderByReleaseDateDesc(): List<MovieEntity>

    @Query("""
        SELECT DISTINCT m FROM MovieEntity m
        JOIN FETCH m.genre g
        LEFT JOIN FETCH m.screenings s
        LEFT JOIN FETCH s.theater t
        WHERE s.screeningTime > :currentTime 
        AND s.status = :status
        AND (:title IS NULL OR m.title LIKE %:title%)
        AND (:genreId IS NULL OR g.id = :genreId)
        ORDER BY m.releaseDate DESC, s.screeningTime ASC
    """)
    fun findMoviesByCurrentTimeAndStatusAndTitleAndGenre(
        @Param("currentTime") currentTime: LocalDateTime,
        @Param("status") status: ScreeningStatus,
        @Param("title") title: String?,
        @Param("genreId") genreId: Long?
    ): List<MovieProjection>
}