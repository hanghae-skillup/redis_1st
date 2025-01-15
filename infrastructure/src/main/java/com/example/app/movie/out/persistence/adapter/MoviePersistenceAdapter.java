package com.example.app.movie.out.persistence.adapter;

import com.example.app.movie.domain.Movie;
import com.example.app.movie.dto.request.MovieSearchRequest;
import com.example.app.movie.out.persistence.mapper.MovieMapper;
import com.example.app.movie.out.persistence.port.LoadMoviePort;
import com.example.app.movie.out.persistence.repository.MovieRepository;
import com.example.app.movie.type.MovieGenre;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.app.movie.out.persistence.entity.QMovieJpaEntity.movieJpaEntity;
import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class MoviePersistenceAdapter implements LoadMoviePort {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public List<Movie> loadAllMovies(MovieSearchRequest movieSearchRequest) {
        return movieRepository.findAllBy(toPredicate(movieSearchRequest))
                .stream()
                .map(movieMapper::MovieJpaEntityToMovie)
                .toList();
    }

    private Predicate toPredicate(MovieSearchRequest movieSearchRequest) {
        return ExpressionUtils.allOf(
                nonNull(movieSearchRequest.title()) ?
                        movieJpaEntity.title.containsIgnoreCase(movieSearchRequest.title()) : null,
                nonNull(movieSearchRequest.genre()) ?
                        movieJpaEntity.genre.eq(MovieGenre.valueOf(movieSearchRequest.genre())) : null
        );
    }
}
