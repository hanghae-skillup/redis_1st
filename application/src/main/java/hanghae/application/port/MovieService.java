package hanghae.application.port;

import hanghae.application.dto.MovieResponse;

import java.util.List;

public interface MovieService {
    List<MovieResponse> findMoviesPlaying();
}
