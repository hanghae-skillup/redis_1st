package com.example.infrastructure.db;

import com.example.domain.model.entity.Screening;
import com.example.domain.model.entity.ScreeningSeat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ScreeningSeatJpaRepository extends JpaRepository<ScreeningSeat, Long> {
    List<ScreeningSeat> findByScreeningAndSeat_IdIn(Screening screening, List<Long> seatIds);

    @Modifying
    @Transactional
    @Query("UPDATE ScreeningSeat s SET s.reserved = false, s.version = 0")
    void resetAllReservations();

    long countByScreeningIdAndReservedIsTrue(Long screeningId);

}
