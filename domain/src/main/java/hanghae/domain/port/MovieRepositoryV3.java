package hanghae.domain.port;

import hanghae.domain.domain.Movie;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieRepositoryV3 {
    List<Movie> findMoviesPlayingWithFilters(
            LocalDateTime now,
            String title,
            String genre
    );
}
