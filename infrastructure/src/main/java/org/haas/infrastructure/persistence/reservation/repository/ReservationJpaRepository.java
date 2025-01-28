package org.haas.infrastructure.persistence.reservation.repository;

import org.haas.infrastructure.persistence.reservation.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Long> {
}
