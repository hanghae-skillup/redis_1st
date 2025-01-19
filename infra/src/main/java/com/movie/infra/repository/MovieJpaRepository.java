package com.movie.infra.repository;

import com.movie.domain.entity.Movie;
import com.movie.domain.repository.MovieRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieJpaRepository extends JpaRepository<Movie, Long>, MovieRepository {
}