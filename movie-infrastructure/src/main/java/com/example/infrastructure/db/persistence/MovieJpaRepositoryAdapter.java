package com.example.infrastructure.db.persistence;

import com.example.application.dto.request.MovieSearchCriteria;
import com.example.application.port.out.MovieRepositoryPort;
import com.example.domain.model.entity.Movie;
import com.example.infrastructure.db.MovieJpaRepository;
import com.example.infrastructure.db.querydsl.MovieRepositoryCustomImpl;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MovieJpaRepositoryAdapter implements MovieRepositoryPort {

    private final MovieJpaRepository movieJpaRepository;
    private final MovieRepositoryCustomImpl movieRepositoryCustom;

    @Override
    public List<Movie> findBy(MovieSearchCriteria movieSearchCriteria, Sort sort) {
        return movieRepositoryCustom.findByFilters(movieSearchCriteria, sort);
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
