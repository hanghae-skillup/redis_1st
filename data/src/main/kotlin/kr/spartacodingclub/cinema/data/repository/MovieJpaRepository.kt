package kr.spartacodingclub.cinema.data.repository

import kr.spartacodingclub.cinema.data.entity.MovieEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface MovieJpaRepository : JpaRepository<MovieEntity, Long> {
    fun findAllByOrderByReleaseDateDesc(): List<MovieEntity>

    @Query("""
        SELECT DISTINCT m 
        FROM MovieEntity m 
        LEFT JOIN FETCH m.screenings s 
        LEFT JOIN FETCH s.theater 
        ORDER BY m.releaseDate DESC
    """)
    fun findAllWithScreeningsAndTheaterOrderByReleaseDateDesc(): List<MovieEntity>

    @Query("""
        SELECT DISTINCT m 
        FROM MovieEntity m 
        LEFT JOIN FETCH m.screenings s 
        LEFT JOIN FETCH s.theater 
        WHERE m.id = :id
    """)
    fun findByIdWithScreeningsAndTheater(@Param("id") id: Long): Optional<MovieEntity>
}
