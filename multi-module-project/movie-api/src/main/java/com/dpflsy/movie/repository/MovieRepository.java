package com.dpflsy.movie.repository;

import com.dpflsy.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByOrderByReleaseDateDesc();

    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.schedules WHERE m.id = :id")
    Movie findMovieWithSchedules(@Param("id") Long id);
}
