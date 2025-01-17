package project.redis.infrastructure.movie.inbound.port;

import java.util.List;
import project.redis.domain.movie.Movie;

public interface MovieQueryPort {
    List<Movie> getMovies();
}
