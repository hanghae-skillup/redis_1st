package org.example.repository;

import org.example.domain.reservation.Reservation;
import org.example.dto.SeatsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
    @Query("select r.id from Reservation r where r.screenScheduleId=:screenScheduleId")
    Long findIdByScreenScheduleId(@Param("screenScheduleId") Long screenScheduleId);
}
