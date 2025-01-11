package com.hanghae.infrastructure.repository;

import com.hanghae.infrastructure.entity.ScreeningScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningScheduleRepositoryJpa extends JpaRepository<ScreeningScheduleEntity, Long> {
}
