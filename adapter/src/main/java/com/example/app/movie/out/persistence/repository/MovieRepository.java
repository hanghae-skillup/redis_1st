package com.example.app.movie.out.persistence.repository;

import com.example.app.movie.out.persistence.entity.MovieJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieJpaEntity, Long>, MovieRepositoryCustom {
}
