package com.movie.storage.movie.repository;

import com.movie.storage.movie.entity.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterJpaRepository extends JpaRepository<TheaterEntity, Long> {
}
