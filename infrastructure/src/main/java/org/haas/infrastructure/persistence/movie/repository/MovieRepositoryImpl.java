package org.haas.infrastructure.persistence.movie.repository;

import lombok.RequiredArgsConstructor;
import org.haas.core.domain.exception.MovieException;
import org.haas.core.domain.movie.Movie;
import org.haas.core.domain.movie.MovieStatus;
import org.haas.core.domain.movie.repository.MovieRepository;
import org.haas.infrastructure.persistence.movie.entity.MovieEntity;
import org.haas.infrastructure.persistence.movie.mapper.MovieMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private final MovieJpaRepository movieJpaRepository;
    private final MovieMapper movieMapper;

    @Override
    public Movie findById(Long id) {
        return movieJpaRepository.findById(id)
                .map(movieMapper::toDomain)
                .orElseThrow( () -> new MovieException("영화를 찾을 수 없습니다. ID: " + id));
    }

    @Override
    public Movie save(Movie movie) {
        MovieEntity movieEntity = movieMapper.toEntity(movie);
        MovieEntity savedMovie = movieJpaRepository.save(movieEntity);
        return movieMapper.toDomain(savedMovie);
    }

    @Override
    public List<Movie> findAllStatusShowingMovies() {
        List<MovieEntity> movieEntities = movieJpaRepository.findAllByMovieStatus(MovieStatus.SHOWING);
        return movieEntities
                .stream()
                .map(movieMapper::toDomain)
                .collect(toList())
                ;
    }

    @Override
    public List<Movie> findAllByMovieStatusOrderByReleasedAtDesc() {
        List<MovieEntity> movieEntities = movieJpaRepository.findAllByMovieStatusOrderByReleasedAtDesc(MovieStatus.SHOWING);
        return movieEntities
                .stream()
                .map(movieMapper::toDomain)
                .collect(toList())
                ;
    }


}
