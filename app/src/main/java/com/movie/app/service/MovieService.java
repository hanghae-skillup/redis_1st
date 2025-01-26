package com.movie.app.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.app.domain.Movie;
import com.movie.app.domain.MovieRepository;
import com.movie.app.domain.MovieRequestDto;
import com.movie.app.domain.TicketingRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Cacheable(value = "Movies", key = "#title", cacheManager = "contentCacheManager")
    public List<Movie> getMoviesByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Cacheable(value = "Movies", key = "#genre", cacheManager = "contentCacheManager")
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    @Cacheable(value = "Movies", key = "all", cacheManager = "contentCacheManager")
    public List<Movie> getMoviesAll() {
        return movieRepository.findAll();
    }

    public Movie postMovie(MovieRequestDto requestDto) {
        Movie movie = new Movie(requestDto);
        movieRepository.save(movie);
        return movie;
    }

    @Transactional
    public Movie ticketing(Long id, TicketingRequestDto requestDto) {
        Movie movie = movieRepository.findById(id).orElseThrow(
                () -> new NullPointerException("There is no id at DB.")
        );

        movie.updateSeats(requestDto.getSeats());
        return movie;
    }

}
