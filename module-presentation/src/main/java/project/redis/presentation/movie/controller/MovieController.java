package project.redis.presentation.movie.controller;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.redis.presentation.movie.dto.response.MovieResponse;
import project.redis.application.movie.port.inbound.MovieQueryUseCase;
import project.redis.domain.movie.Movie;
import project.redis.presentation.movie.mapper.MovieApiMapper;


@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    public final MovieQueryUseCase movieQueryUseCase;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getMovie() {
        List<Movie> movies = movieQueryUseCase.getMovies();

        List<MovieResponse> result = movies.stream()
                .map(MovieApiMapper::toMovieResponse)
                .toList();

        return ResponseEntity.ok(result);
    }
}
