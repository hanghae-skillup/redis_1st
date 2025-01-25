package com.example.jpa.repository.reservation;

import com.example.jpa.entity.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query("SELECT COUNT(r) FROM Reservation r " +
            "JOIN Seat s ON s.reservationId = r.id " +
            "JOIN Theater t ON s.theaterId = t.id " +
            "JOIN Screening sc ON sc.theaterId = t.id " +
            "WHERE r.userId = :userId AND sc.id = :screeningId")
    Long getReservationCount(@Param("userId") Long userId, @Param("screeningId") Long screeningId);

}
