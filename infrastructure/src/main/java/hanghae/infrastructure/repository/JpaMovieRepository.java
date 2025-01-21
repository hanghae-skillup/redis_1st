package hanghae.infrastructure.repository;

import hanghae.infrastructure.domain.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaMovieRepository extends JpaRepository<MovieEntity, Long> {
    @Query("SELECT m FROM MovieEntity m " +
            "WHERE m.releaseDate <= :now " +
            "AND (m.deletedAt IS NULL OR m.deletedAt > :now)" +
            "ORDER BY m.releaseDate DESC")
        // 상영 중인 영화 -> release_date <= now <= deleted_at
    Optional<List<MovieEntity>> findMoviesPlaying(@Param("now") LocalDateTime now);
}
