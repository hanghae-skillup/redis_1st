package com.example.infrastructure.db;

import com.example.domain.model.entity.ReservedSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedSeatJpaRepository extends JpaRepository<ReservedSeat, Long> {
}
