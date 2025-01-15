package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.movie.Movie;
import org.example.dto.response.movie.PlayingMoviesResponseDto;
import org.example.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<PlayingMoviesResponseDto> getPlayingMovies() {
        List<Movie> allPlayingMovies = movieRepository.findAllPlayingMovies();

        return allPlayingMovies.stream().map(movie -> new PlayingMoviesResponseDto(
                movie.getTitle(),
                movie.getThumbnail(),
                movie.getGenre(),
                movie.getAgeRating(),
                movie.getReleaseDate(),
                movie.getRunningTime(),
                movieRepository.findScreenSchedules(movie.getId())
        )).collect(Collectors.toList());
    }
}
