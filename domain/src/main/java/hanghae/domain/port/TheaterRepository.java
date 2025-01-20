package hanghae.domain.port;

import hanghae.domain.domain.Movie;
import hanghae.domain.domain.Theater;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheaterRepository {
    Optional<List<Theater>> findTheaterByMovie(Movie movie);
}
