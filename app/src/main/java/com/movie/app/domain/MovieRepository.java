package com.movie.app.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>{
    List<Movie> findByTitle(String title);
    List<Movie> findByGenre(String genre);
}
