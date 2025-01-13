package com.movie.storage.movie.repository;

import com.movie.storage.movie.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

public interface MovieJpaRepository extends CrudRepository<MovieEntity, Long> {



}
