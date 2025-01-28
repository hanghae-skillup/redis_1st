package com.hanghae.infrastructure.repository;

import com.hanghae.infrastructure.entity.ScreenSeatLayoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScreenSeatLayoutRepositoryJpa extends JpaRepository<ScreenSeatLayoutEntity, Long> {
    @Query("SELECT s FROM ScreenSeatLayoutEntity s WHERE s.seatRow = :seatRow AND s.screenEntity.id = :screenId")
    ScreenSeatLayoutEntity findOneBySeatRowAndScreenId(@Param("seatRow") String seatRow, @Param("screenId") Long screenId);
}
