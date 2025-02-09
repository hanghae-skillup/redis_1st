package org.example.repository;

import org.example.domain.seat.Col;
import org.example.domain.seat.Row;
import org.example.domain.seat.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SeatJpaRepository extends JpaRepository<Seat, Long> {
    @Query("select s from Seat s where s.screenRoomId=:screenRoomId and s.row=:row and s.col=:col")
    Optional<Seat> findSeats(@Param("screenRoomId")Long screenRoomId,
                              @Param("row") Row row,
                              @Param("col") Col col);
}
