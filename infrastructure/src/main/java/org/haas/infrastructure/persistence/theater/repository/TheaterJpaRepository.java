package org.haas.infrastructure.persistence.theater.repository;

import org.haas.infrastructure.persistence.theater.entity.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterJpaRepository extends JpaRepository<TheaterEntity, Long> {
}