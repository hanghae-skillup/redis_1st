package com.example.infrastructure.persistence;

import com.example.application.port.out.MovieRepositoryPort;
import com.example.domain.model.entity.Movie;
import com.example.domain.model.valueObject.Genre;
import com.example.infrastructure.db.MovieJpaRepository;
import com.example.infrastructure.db.querydsl.MovieRepositoryCustomImpl;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

/**
 * This adapter serves as a bridge between the application layer
 * and the database layer, allowing the application to access movie
 * data through the defined MovieRepositoryPort interface while
 * encapsulating the JPA-specific implementation details.
 */

@RequiredArgsConstructor
@Repository
public class MovieJpaRepositoryAdapter implements MovieRepositoryPort {

    private final MovieJpaRepository movieJpaRepository;
    private final MovieRepositoryCustomImpl movieRepositoryCustom;

    @Override
    public List<Movie> findBy(String title, Genre genre, Sort sort) {
        return movieRepositoryCustom.findByFilters(title, genre, sort);
    }

    @Override
    public void save(Movie movie) {
        movieJpaRepository.save(movie);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieJpaRepository.findById(id);
    }

}
