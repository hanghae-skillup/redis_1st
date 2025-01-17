package com.hanghae.cinema.application.service;

import com.hanghae.cinema.application.usecase.FindMovieUseCase;
import com.hanghae.cinema.application.dto.MovieResponseDTO;
import com.hanghae.cinema.application.mapper.MovieMapper;
import com.hanghae.cinema.domain.model.Movie;
import com.hanghae.cinema.application.port.FindMoviePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService implements FindMovieUseCase {

    private final FindMoviePort findMoviePort;
    private final MovieMapper movieMapper;

    @Override
    public List<MovieResponseDTO> getMovies() {

        LocalDate today = LocalDate.now();
        List<Movie> movieList = findMoviePort.getMovieList(today);
        return movieMapper.toResponseDTOList(movieList);
    }
}