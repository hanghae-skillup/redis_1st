package com.movie.domain.facade;

import com.movie.domain.movie.dto.command.ReservationCommand;
import com.movie.redis.lock.DistributedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationManager {

    private final ReservationFacade reservationFacade;

    @DistributedLock(lockName = "reservation")
    public void makeReservationByDistributedLock(String token, Long scheduleId, List<Long> seatIds) {
        ReservationCommand.GetReserveData reserveData = ReservationCommand.GetReserveData.of(scheduleId, seatIds, token);
        reservationFacade.makeReservation(reserveData);
    }

}
