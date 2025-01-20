package hanghae.application.service;

import hanghae.application.dto.MovieResponse;
import hanghae.application.port.MovieService;
import hanghae.common.exception.NoContentsException;
import hanghae.domain.domain.Movie;
import hanghae.domain.port.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Primary
@Service
@RequiredArgsConstructor
public class MovieServiceImplV2 implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<MovieResponse> findMoviesPlaying() {
        List<Movie> movies = movieRepository.findMoviesPlaying(LocalDateTime.now())
                .orElseThrow(() -> new NoContentsException("movie"));

        return movies.stream()
                .map(MovieResponse::from)
                .toList();
    }
}
