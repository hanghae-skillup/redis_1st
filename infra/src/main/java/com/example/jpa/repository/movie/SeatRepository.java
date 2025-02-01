package com.example.jpa.repository.movie;

import com.example.jpa.entity.theater.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Seat s " +
            "JOIN Theater t ON s.theaterId = t.id " +
            "JOIN Screening sc ON sc.theaterId = t.id " +
            "WHERE s.position = :position AND sc.id = :screeningId")
    Optional<Seat> findByPositionAndScreeningId(@Param("position") String position,
                                                @Param("screeningId") Long screeningId);

}
