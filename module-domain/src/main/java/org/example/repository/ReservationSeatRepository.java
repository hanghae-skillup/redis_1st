package org.example.repository;

import jakarta.persistence.LockModeType;
import org.example.domain.reservationseat.ReservationSeat;
import org.example.dto.SeatsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationSeatRepository extends JpaRepository<ReservationSeat, Long> {
    @Query("select new org.example.dto.SeatsDto(s.row, s.col) " +
            "from ReservationSeat rs " +
            "join Seat s on rs.seatId=s.id " +
            "join Reservation r on rs.reservationId = r.id " +
            "where r.usersId=:userId and r.screenScheduleId=:screenScheduleId")
    List<SeatsDto> findReservedSeatByUserIdAndScreenScheduleId(@Param("userId") Long userId, @Param("screenScheduleId") Long screenScheduleId);

    @Query("select rs.id " +
            "from ReservationSeat rs " +
            "join Seat s on rs.seatId=s.id " +
            "join Reservation r on rs.reservationId = r.id " +
            "where r.screenScheduleId=:screenScheduleId")
    List<Long> findReservedSeatByScreenScheduleId(@Param("screenScheduleId") Long screenScheduleId);

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select rs from ReservationSeat rs join Reservation r on rs.reservationId=r.id " +
            "where r.screenScheduleId=:screenScheduleId and rs.seatId = :seatId")
    Optional<ReservationSeat> findReservedSeatBySeatId(@Param("screenScheduleId") Long screenScheduleId, @Param("seatId") Long seatId);
}
