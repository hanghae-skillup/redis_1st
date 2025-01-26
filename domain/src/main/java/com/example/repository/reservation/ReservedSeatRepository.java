package com.example.repository.reservation;

import com.example.entity.reservation.ReservedSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedSeatRepository extends JpaRepository<ReservedSeat, Long>, ReservedSeatRepositoryCustom {
}
