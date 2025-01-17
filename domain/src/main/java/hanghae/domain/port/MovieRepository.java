package hanghae.domain.port;

import hanghae.domain.domain.Movie;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository {
    Optional<List<Movie>> findMoviesPlaying(LocalDate now);
}