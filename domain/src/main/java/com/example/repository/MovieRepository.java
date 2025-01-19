package com.example.repository;

import com.example.entity.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m JOIN fetch m.screenings s JOIN FETCH m.movieTheaters mt JOIN FETCH mt.theater")
    List<Movie> findMovieWithScreeningAndTheater(Sort sort);
}
