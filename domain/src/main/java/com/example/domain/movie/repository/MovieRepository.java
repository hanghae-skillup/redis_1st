package com.example.domain.movie.repository;


import com.example.domain.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

}
