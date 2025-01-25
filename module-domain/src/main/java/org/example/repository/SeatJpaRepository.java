package org.example.repository;

import org.example.domain.seat.Col;
import org.example.domain.seat.Row;
import org.example.domain.seat.Seat;
import org.example.dto.ReservedSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatJpaRepository extends JpaRepository<Seat, Long> {
    @Query("select count(s) > 0 from Seat s where s.row = :row and s.col = :col and s.screenScheduleId = :screenScheduleId")
    boolean existsReservedSeat(@Param("row") Row row, @Param("col") Col col, @Param("screenScheduleId") Long screenScheduleId);

    @Query("select new org.example.dto.ReservedSeats(s.row, s.col) from Seat s join Reservation r on s.reservationId=r.id join Users u on u.id=:userId where s.screenScheduleId=:screenScheduleId")
    List<ReservedSeats> findReservedSeatByUserId(@Param("userId") Long userId, @Param("screenScheduleId") Long screenScheduleId);
}
