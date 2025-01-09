package com.example.app.movie.service;

import com.example.app.movie.entity.Movie;
import com.example.app.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieRepoService {

    private final MovieRepository movieRepository;

    public List<Movie> getMovies(final LocalDate showDate) {
        return movieRepository.findAllByReleaseDateLessThanEqual(
                showDate,
                Sort.by(Sort.Direction.DESC, "releaseDate"));
    }
}
