package com.example.infrastructure.db;

import com.example.domain.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieJpaRepository extends JpaRepository<Movie, Long> {
}
