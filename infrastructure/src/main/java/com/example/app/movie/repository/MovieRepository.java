package com.example.app.movie.repository;

import com.example.app.movie.entity.Movie;
import com.example.app.movie.type.MovieStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByReleaseDateLessThanEqualAndStatus(LocalDate showDate, MovieStatus status, Sort sort);
}
