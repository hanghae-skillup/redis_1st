package com.hanghae.infrastructure.repository;

import com.hanghae.infrastructure.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepositoryJpa extends JpaRepository<MovieEntity, Long> {
}
