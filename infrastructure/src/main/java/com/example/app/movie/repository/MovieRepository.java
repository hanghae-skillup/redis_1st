package com.example.app.movie.repository;

import com.example.app.movie.entity.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByReleaseDateLessThanEqual(LocalDate showDate, Sort sort);
}
