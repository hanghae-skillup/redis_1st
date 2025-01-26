package com.movie.app.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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
    private final RedissonClient redissonClient;

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

        if(movie==null) {
            return movie;
        }

        RLock lock = redissonClient.getLock(id.toString());
        try {
            boolean acquireLock = lock.tryLock(10, 1, TimeUnit.SECONDS);
            if (!acquireLock) {
                System.out.println("Lock get fail");
                return movie;
            }
            movie.updateSeats(requestDto.getSeats());
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }

        return movie;
    }

}
