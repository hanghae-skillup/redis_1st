package hanghae.application.service;

import hanghae.application.dto.MovieResponse;
import hanghae.application.port.MovieServiceV3;
import hanghae.common.exception.NoContentsException;
import hanghae.domain.domain.Movie;
import hanghae.domain.port.MovieRepositoryV3;
import hanghae.domain.types.movie.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImplV3 implements MovieServiceV3 {

    private final MovieRepositoryV3 movieRepository;

    @Override
    public List<MovieResponse> findMoviesPlayingWithFilters(String title, String genre) {
        List<Movie> movies =
                movieRepository.findMoviesPlayingWithFilters(LocalDateTime.now(), title, genre);

        return movies.stream()
                .map(MovieResponse::from)
                .toList();
    }
}
