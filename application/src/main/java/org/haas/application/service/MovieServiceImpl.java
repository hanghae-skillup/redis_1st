package org.haas.application.service;

import lombok.RequiredArgsConstructor;
import org.haas.core.domain.movie.Movie;
import org.haas.core.domain.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<MovieDto> getAllMovieStatusIsShowing() {
        List<Movie> movies = movieRepository.findAllStatusShowing();
        return movies.stream()
    }

    @Override
    public MovieDto saveMovie(MovieDto movieDto) {
        Movie movie = movieMapper.toDomain(movieDto);
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.toDto(savedMovie);
    }
}
