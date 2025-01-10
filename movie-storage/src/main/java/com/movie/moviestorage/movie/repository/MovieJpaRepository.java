package com.movie.moviestorage.movie.repository;

import com.movie.moviestorage.movie.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

public interface MovieJpaRepository extends CrudRepository<MovieEntity, Long> {



}
