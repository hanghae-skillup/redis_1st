package com.example.app.movie.out.persistence.adapter;

import com.example.app.movie.Movie;
import com.example.app.movie.out.persistence.mapper.MovieMapper;
import com.example.app.movie.out.persistence.port.LoadMoviePort;
import com.example.app.movie.out.persistence.repository.MovieRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MoviePersistenceAdapter implements LoadMoviePort {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public List<Movie> loadAllMovies(Predicate predicate) {
        return movieRepository.findAllBy(predicate)
                .stream()
                .map(movieMapper::MovieJpaEntityToMovie)
                .toList();
    }
}
