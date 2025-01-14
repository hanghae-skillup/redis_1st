package com.example.app.movie.out.persistence.repository;

import com.example.app.movie.out.persistence.entity.MovieJpaEntity;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface MovieRepositoryCustom {

    List<MovieJpaEntity> findAllBy(Predicate predicate);
}
