package org.haas.infrastructure.persistence.movie.repository;

import org.haas.infrastructure.persistence.movie.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, Long> {

    List<MovieEntity> findAllByMovieStatus_Showing();
}
