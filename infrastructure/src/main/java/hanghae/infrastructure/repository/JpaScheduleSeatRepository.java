package hanghae.infrastructure.repository;

import hanghae.domain.entity.ScheduleSeat;
import hanghae.domain.type.ScheduleSeatId;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaScheduleSeatRepository extends JpaRepository<ScheduleSeat, ScheduleSeatId> {

    // 비관적 락 적용
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(
            "select ss from ScheduleSeat ss " +
            "where ss.schedule.scheduleId = :scheduleId " +
            "and ss.seat.seatId = :seatId"
    )
    Optional<ScheduleSeat> findScheduleSeatByScheduleIdAndSeatIdWithPessimisticLock(
            @Param("scheduleId") Long scheduleId,
            @Param("seatId") Long seatId);

    // 낙관적 락 적용
    @Lock(LockModeType.OPTIMISTIC)
    @Query(
            "select ss from ScheduleSeat ss " +
                    "where ss.schedule.scheduleId = :scheduleId " +
                    "and ss.seat.seatId = :seatId"
    )
    Optional<ScheduleSeat> findScheduleSeatByScheduleIdAndSeatId(
            @Param("scheduleId") Long scheduleId,
            @Param("seatId") Long seatId);
}
