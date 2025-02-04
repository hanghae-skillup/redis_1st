package com.movie.domain.repository;

import com.movie.domain.entity.Reservation;
import com.movie.domain.entity.ReservationStatus;
import com.movie.domain.entity.Schedule;
import com.movie.domain.entity.Seat;
import com.movie.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reservation r JOIN r.seats s WHERE r.schedule.id = :scheduleId AND s.id = :seatId")
    boolean existsByScheduleIdAndSeatId(@Param("scheduleId") Long scheduleId, @Param("seatId") Long seatId);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.user.id = :userId AND r.schedule.id = :scheduleId AND r.status = :status")
    long countByUserIdAndScheduleIdAndStatus(@Param("userId") Long userId, @Param("scheduleId") Long scheduleId, @Param("status") ReservationStatus status);

    @Query("SELECT r FROM Reservation r WHERE r.user.id = :userId AND r.schedule.id = :scheduleId AND r.status = :status")
    List<Reservation> findByUserIdAndScheduleIdAndStatus(@Param("userId") Long userId, @Param("scheduleId") Long scheduleId, @Param("status") ReservationStatus status);

    @Query("SELECT r FROM Reservation r WHERE r.user.id = :userId")
    List<Reservation> findByUserId(@Param("userId") Long userId);

    Optional<Reservation> findByReservationNumber(String reservationNumber);

    List<Reservation> findBySchedule(Schedule schedule);
} 