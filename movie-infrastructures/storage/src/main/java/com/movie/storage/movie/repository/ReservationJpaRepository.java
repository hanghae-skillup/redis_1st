package com.movie.storage.movie.repository;

import com.movie.storage.movie.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findById_ScheduleIdAndId_SeatIdIn(Long id_ScheduleId, List<Long> id_SeatId);

    @Modifying
    @Query("update ReservationEntity r set r.userId = :userId, r.reservedAt = :reservedAt where r.id.scheduleId = :scheduleId and r.id.seatId in :seatIds")
    void updateAsReserved(@Param("scheduleId") Long scheduleId,
                          @Param("seatIds") List<Long> seatIds,
                          @Param("userId") Long userId,
                          @Param("reservedAt") LocalDateTime reservedAt);
}
