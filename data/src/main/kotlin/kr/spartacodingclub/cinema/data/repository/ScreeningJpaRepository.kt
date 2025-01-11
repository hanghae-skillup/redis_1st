package kr.spartacodingclub.cinema.data.repository

import kr.spartacodingclub.cinema.data.entity.ScreeningEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ScreeningJpaRepository : JpaRepository<ScreeningEntity, Long> {
    @Query("""
        SELECT DISTINCT s
        FROM ScreeningEntity s 
        LEFT JOIN FETCH s.theater 
        WHERE s.movie.id = :movieId 
        ORDER BY s.startTime
    """)
    fun findAllByMovieIdOrderByStartTimeAsc(movieId: Long): List<ScreeningEntity>
}
