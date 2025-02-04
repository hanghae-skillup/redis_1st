package com.movie.domain.repository;

import com.movie.domain.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m WHERE m.releaseDate <= :now ORDER BY m.releaseDate DESC")
    List<Movie> findCurrentMovies(@Param("now") LocalDateTime now);

    @Query("SELECT m FROM Movie m WHERE m.releaseDate > :now ORDER BY m.releaseDate ASC")
    List<Movie> findUpcomingMovies(@Param("now") LocalDateTime now);
} 