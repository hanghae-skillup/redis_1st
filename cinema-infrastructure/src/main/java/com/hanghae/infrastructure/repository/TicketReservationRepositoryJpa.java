package com.hanghae.infrastructure.repository;

import com.hanghae.domain.model.enums.ScreenSeat;
import com.hanghae.infrastructure.entity.TicketReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketReservationRepositoryJpa extends JpaRepository<TicketReservationEntity, Long> {
    int countByScreeningScheduleEntityIdAndMemberEntityId(Long scheduleId, Long memberId);


    @Query("SELECT COUNT(t) FROM TicketReservationEntity t " +
            "WHERE t.screeningScheduleEntity.id = :scheduleId " +
            "AND t.screenSeat IN :screenSeats")
    int countByScheduleIdAndScreenSeats(@Param("scheduleId") Long scheduleId,
                                        @Param("screenSeats") List<ScreenSeat> screenSeats);
}
