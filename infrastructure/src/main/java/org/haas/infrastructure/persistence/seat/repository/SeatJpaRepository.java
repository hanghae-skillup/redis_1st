package org.haas.infrastructure.persistence.seat.repository;

import org.haas.infrastructure.persistence.seat.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatJpaRepository extends JpaRepository<SeatEntity, Long> {
}
