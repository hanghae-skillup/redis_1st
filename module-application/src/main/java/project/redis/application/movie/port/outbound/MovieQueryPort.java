package project.redis.application.movie.port.outbound;

import java.util.List;
import project.redis.domain.movie.Movie;

public interface MovieQueryPort {
    List<Movie> getMovies();
}
