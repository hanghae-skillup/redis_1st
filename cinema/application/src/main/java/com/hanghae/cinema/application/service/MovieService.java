package com.hanghae.cinema.application.service;

import com.hanghae.cinema.application.dto.MovieResponseDTO;
import com.hanghae.cinema.application.port.FindMoviePort;
import com.hanghae.cinema.application.usecase.FindMovieUseCase;
import com.hanghae.cinema.domain.type.MovieGenre;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService implements FindMovieUseCase {

    private final FindMoviePort findMoviePort;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "movie_title_genre", key = "#keyword + '-' + #genre")
    public List<MovieResponseDTO> getMovies(String keyword, MovieGenre genre) {

        LocalDateTime today = LocalDateTime.now();

        return findMoviePort.getMovie(today, keyword, genre);
    }
}