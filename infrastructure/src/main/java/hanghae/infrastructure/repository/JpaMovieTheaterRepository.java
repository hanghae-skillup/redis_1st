package hanghae.infrastructure.repository;

import hanghae.infrastructure.domain.entity.MovieTheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMovieTheaterRepository extends JpaRepository<MovieTheaterEntity, Long> {
}
