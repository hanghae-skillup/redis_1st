package hanghae.infrastructure.repository;

import hanghae.infrastructure.domain.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaMovieRepositoryV2 extends JpaRepository<MovieEntity, Long> {
    @Query("""
            SELECT DISTINCT m
            FROM MovieEntity m
            LEFT JOIN FETCH m.showtime s
            LEFT JOIN FETCH s.theaterShowtime ts
            LEFT JOIN FETCH ts.theater t
            WHERE m.releaseDate <= :now
            AND (m.deletedAt IS NULL OR m.deletedAt > :now)
            ORDER BY m.releaseDate DESC
    """)
    Optional<List<MovieEntity>> findMoviesPlayingV2(@Param("now") LocalDateTime now);
}
