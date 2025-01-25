package com.example.domain.seat.repository;

import com.example.domain.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {

    @Query("SELECT s FROM Seat s WHERE s.theater.id = :theaterId AND s.id IN :seatIdList AND s.status = 'AVAILABLE'")
    List<Seat> findAvailableSeats(Long theaterId, List<Long> seatIdList);
}