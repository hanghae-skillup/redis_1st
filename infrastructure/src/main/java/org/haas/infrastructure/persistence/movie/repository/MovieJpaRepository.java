package org.haas.infrastructure.persistence.movie.repository;

import org.haas.core.domain.movie.MovieStatus;
import org.haas.infrastructure.persistence.movie.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, Long> {

    List<MovieEntity> findAllByMovieStatus(MovieStatus movieStatus);

    @Query(
            " SELECT m FROM movies m " +
            " WHERE m.movieStatus = :movieStatus " +
            " ORDER BY m.releasedAt DESC "
    )
    List<MovieEntity> findAllByMovieStatusOrderByReleasedAtDesc(
            @Param("movieStatus") MovieStatus movieStatus
    );
}
