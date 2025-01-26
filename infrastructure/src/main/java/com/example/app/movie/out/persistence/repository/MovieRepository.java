package com.example.app.movie.out.persistence.repository;

import com.example.app.movie.out.persistence.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long>, MovieRepositoryCustom {
}
