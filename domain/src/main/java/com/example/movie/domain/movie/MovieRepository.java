package com.example.movie.domain.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
