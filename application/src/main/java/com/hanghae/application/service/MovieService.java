package com.hanghae.application.service;


import com.hanghae.common.dto.MovieResponseDto;
import com.hanghae.domain.repository.MovieRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public List<MovieResponseDto> getMovies() {
        return movieRepository.findMoviesWithDetails().stream()
                .map(movie -> new MovieResponseDto(
                        movie.getTitle(),
                        movie.getRating(),
                        movie.getReleaseDate(),
                        movie.getThumbnailUrl(),
                        movie.getDuration(),
                        movie.getGenre().getGenreName(),
                        movie.getShowtimes().stream()
                                .map(showtime -> new MovieResponseDto.ShowtimeDto(
                                        showtime.getTheater().getTheaterName(),
                                        showtime.getStartTime(),
                                        showtime.getEndTime()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}