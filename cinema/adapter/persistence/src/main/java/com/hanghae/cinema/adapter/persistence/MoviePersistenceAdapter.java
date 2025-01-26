package com.hanghae.cinema.adapter.persistence;

import com.hanghae.cinema.adapter.persistence.repository.MovieRepository;
import com.hanghae.cinema.application.dto.MovieResponseDTO;
import com.hanghae.cinema.application.port.FindMoviePort;
import com.hanghae.cinema.domain.type.MovieGenre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class MoviePersistenceAdapter implements FindMoviePort {

    private final MovieRepository movieRepository;

    public List<MovieResponseDTO> getMovie(LocalDateTime dateTime, String keyword, MovieGenre movieGenre) {
        return movieRepository.findMovie(dateTime.toLocalDate(), dateTime, keyword, movieGenre);
    }
}
