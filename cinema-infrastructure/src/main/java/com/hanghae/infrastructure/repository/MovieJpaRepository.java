package com.hanghae.infrastructure.repository;

import com.hanghae.infrastructure.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, Long> {
}
