package com.example.repository.movie;

import com.example.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>, MovieRepositoryCustom {
}
