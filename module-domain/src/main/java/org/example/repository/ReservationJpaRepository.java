package org.example.repository;

import org.example.domain.reservation.Reservation;
import org.example.dto.ReservedSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
    @Query("select new org.example.dto.ReservedSeats(s.row, s.col) " +
            "from Reservation r join Seat s on r.seatId=s.id " +
            "where r.usersId=:userId and r.screenScheduleId=:screenScheduleId")
    List<ReservedSeats> findReservedSeatByUserIdAndScreenScheduleId(@Param("userId") Long userId, @Param("screenScheduleId") Long screenScheduleId);

    @Query("select s.id " +
            "from Reservation r join Seat s on r.seatId=s.id " +
            "where r.screenScheduleId=:screenScheduleId")
    List<Long> findReservedSeatByScreenScheduleId(@Param("screenScheduleId") Long screenScheduleId);


    boolean existsByUsersIdAndScreenScheduleIdAndSeatId(Long userId, Long screenScheduleId, Long seatId);
}
