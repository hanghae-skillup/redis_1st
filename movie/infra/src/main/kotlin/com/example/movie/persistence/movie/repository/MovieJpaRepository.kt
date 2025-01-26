package com.example.movie.persistence.movie.repository

import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.persistence.movie.model.MovieEntity
import com.example.movie.persistence.movie.dto.MovieWithGenreDto
import com.example.movie.persistence.movie.dto.ScreeningWithTheaterDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface MovieJpaRepository : JpaRepository<MovieEntity, Long> {
    fun findAllByOrderByReleaseDateDesc(): List<MovieEntity>

    @Query(
        """
        SELECT new com.example.movie.persistence.movie.dto.MovieWithGenreDto(
            m.id, m.title, m.rating, m.releaseDate, m.thumbnailUrl, m.runningTime,
            g.id, g.name
        )
        FROM MovieEntity m
        JOIN m.genre g
        WHERE EXISTS (
            SELECT 1 FROM ScreeningEntity s 
            WHERE s.movieId = m.id
            AND s.screeningTime > :currentTime 
            AND s.status = :status
        )
        AND (:title IS NULL OR m.title LIKE %:title%)
        AND (:genreId IS NULL OR g.id = :genreId)
        ORDER BY m.releaseDate DESC
    """
    )
    fun findMoviesByCurrentTimeAndStatusAndTitleAndGenre(
        @Param("currentTime") currentTime: LocalDateTime,
        @Param("status") status: ScreeningStatus,
        @Param("title") title: String?,
        @Param("genreId") genreId: Long?
    ): List<MovieWithGenreDto>

    @Query(
        """
        SELECT new com.example.movie.persistence.movie.dto.ScreeningWithTheaterDto(
            s.id, s.movieId, s.screeningTime, s.screeningEndTime, s.status,
            t.id, t.name
        )
        FROM ScreeningEntity s
        JOIN s.theater t
        WHERE s.movieId IN :movieIds
        AND s.screeningTime > :currentTime
        AND s.status = :status
        ORDER BY s.screeningTime ASC
    """
    )
    fun findScreeningsByMovieIds(
        @Param("movieIds") movieIds: List<Long>,
        @Param("currentTime") currentTime: LocalDateTime,
        @Param("status") status: ScreeningStatus
    ): List<ScreeningWithTheaterDto>
}