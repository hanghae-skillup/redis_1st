package com.movie.domain.movie;

import com.movie.domain.movie.dto.command.ReservationCommand;

public interface ReservationRedisRepository {

    void saveReservationDone(ReservationCommand.CreateCache createCache);

    Long getReservedAtTimeMillis(ReservationCommand.GetCache cache);

}
