package com.example.infrastructure.db;

import com.example.domain.model.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterJpaRepository extends JpaRepository<Theater, Long> {
}
