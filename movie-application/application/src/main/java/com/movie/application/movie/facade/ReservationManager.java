package com.movie.application.movie.facade;

import com.movie.domain.movie.dto.command.ReservationCommand;
import com.movie.redis.lock.DistributedLock;
import com.movie.redis.lock.DistributedLockExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationManager {

    private final ReservationFacade reservationFacade;
    private final DistributedLockExecutor executor;

    /**
        AOP 형태의 분산락 적용
     */
    @DistributedLock(lockName = "reservation")
    public void makeReservationByDistributedLock(String token, Long scheduleId, List<Long> seatIds) {
        ReservationCommand.GetReserveData reserveData = ReservationCommand.GetReserveData.of(scheduleId, seatIds, token);
        reservationFacade.makeReservations(reserveData);
    }

    /**
        함수형 분산락 적용
     */
    public void makeReservationByFunctionalLock(String token, Long scheduleId, List<Long> seatIds) {
        String lockName = "%s:%s:%s".formatted("reservation", System.currentTimeMillis(), token);
        executor.executeWithLock(lockName, () -> {
            ReservationCommand.GetReserveData reserveData = ReservationCommand.GetReserveData.of(scheduleId, seatIds, token);
            reservationFacade.makeReservations(reserveData);
        });
    }

}
