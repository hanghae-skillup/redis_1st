package com.hanghae.infrastructure.repository;

import com.hanghae.infrastructure.entity.TicketReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketReservationRepositoryJpa extends JpaRepository<TicketReservationEntity, Long> {
}
