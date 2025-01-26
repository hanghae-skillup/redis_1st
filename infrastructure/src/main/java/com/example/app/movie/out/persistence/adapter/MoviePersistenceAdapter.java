package com.example.app.movie.out.persistence.adapter;

import com.example.app.movie.domain.Movie;
import com.example.app.movie.dto.MovieSearchCommand;
import com.example.app.movie.out.persistence.mapper.MovieMapper;
import com.example.app.movie.out.persistence.repository.MovieRepository;
import com.example.app.movie.port.LoadMoviePort;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.app.movie.out.persistence.entity.QMovieEntity.movieEntity;
import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class MoviePersistenceAdapter implements LoadMoviePort {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public List<Movie> loadAllMovies(MovieSearchCommand movieSearchCommand) {
        return movieRepository.findAllBy(toPredicate(movieSearchCommand))
                .stream()
                .map(movieMapper::movieEntityToMovie)
                .toList();
    }

    private Predicate toPredicate(MovieSearchCommand movieSearchCommand) {
        return ExpressionUtils.allOf(
                nonNull(movieSearchCommand.title()) ?
                        movieEntity.title.containsIgnoreCase(movieSearchCommand.title()) : null,
                nonNull(movieSearchCommand.genre()) ?
                        movieEntity.genre.eq(movieSearchCommand.genre()) : null
        );
    }
}
