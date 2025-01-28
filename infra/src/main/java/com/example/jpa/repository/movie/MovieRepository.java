package com.example.jpa.repository.movie;

import com.example.jpa.entity.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long>, MovieRepositoryCustom {

}
