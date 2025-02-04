package hanghae.infrastructure.repository;

import hanghae.domain.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMovieRepository extends JpaRepository<Movie, Long> {
}
