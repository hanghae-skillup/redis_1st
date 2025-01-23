package com.movie.domain.movie.dto.command;

import java.util.List;

public class ReservationCommand {

    public static record Reserve(Long scheduleId, List<Long> seatIds, Long userId) {

    }

}
