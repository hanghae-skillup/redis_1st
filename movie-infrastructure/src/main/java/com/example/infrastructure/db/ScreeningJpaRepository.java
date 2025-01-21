package com.example.infrastructure.db;

import com.example.domain.model.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningJpaRepository extends JpaRepository<Screening, Long> {
}
