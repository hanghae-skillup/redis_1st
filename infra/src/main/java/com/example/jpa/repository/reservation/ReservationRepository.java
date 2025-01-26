package com.example.jpa.repository.reservation;

import com.example.jpa.entity.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query("SELECT COUNT(r) FROM Reservation r " +
            "JOIN Seat s ON s.id = r.seatId " +
            "JOIN Screening sc ON sc.id = r.screeningId " +
            "WHERE r.userId = :userId AND sc.id = :screeningId")
    Long getReservationCount(@Param("userId") Long userId, @Param("screeningId") Long screeningId);

    boolean existsBySeatIdAndScreeningId(Long seatId, Long screeningId);

}
