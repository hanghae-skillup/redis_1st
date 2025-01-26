package com.example.infrastructure.seatreservation;

import com.example.domain.seatReservation.entity.SeatReservation;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatReservationJpaRepository extends JpaRepository<SeatReservation,Long> {

    List<SeatReservation> findByUserId(Long userId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<SeatReservation> findSeatReservationBySeatNumberIn(List<String> seats);
}
