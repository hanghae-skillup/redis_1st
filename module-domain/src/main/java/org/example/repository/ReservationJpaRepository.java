package org.example.repository;

import org.example.domain.reservation.Reservation;
import org.example.dto.ReservedSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
}
