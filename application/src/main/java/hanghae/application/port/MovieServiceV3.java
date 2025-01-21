package hanghae.application.port;

import hanghae.application.dto.MovieResponse;

import java.util.List;

public interface MovieServiceV3 {
    List<MovieResponse> findMoviesPlayingWithFilters(String title, String genre);
}
