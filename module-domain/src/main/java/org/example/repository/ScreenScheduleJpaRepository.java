package org.example.repository;

import org.example.domain.screenschedule.ScreenSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScreenScheduleJpaRepository extends JpaRepository<ScreenSchedule, Long> {
    @Query("select ss.screenRoomId from ScreenSchedule ss where ss.id = :id")
    Long findScreenRoomIdById(@Param("id") Long id);
}
