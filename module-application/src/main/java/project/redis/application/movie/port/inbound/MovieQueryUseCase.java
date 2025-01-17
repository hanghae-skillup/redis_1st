package project.redis.application.movie.port.inbound;

import java.util.List;
import project.redis.domain.movie.Movie;

public interface MovieQueryUseCase {

    List<Movie> getMovies();
}
