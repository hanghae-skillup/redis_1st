package com.hanghae.infrastructure.repository;

import com.hanghae.infrastructure.entity.ScreenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRepositoryJpa extends JpaRepository<ScreenEntity, Long> {
}
