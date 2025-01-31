package com.hanghae.application.port.out;

import com.hanghae.domain.model.enums.ScreenSeat;

import java.util.List;
import java.util.function.Supplier;

public interface RedissonLockPort {
    <T> T executeWithSeatLock(Long scheduleId, ScreenSeat seat, Supplier<T> action);
    <T> T executeWithSeatsLocks(Long scheduleId, List<ScreenSeat> seats, Supplier<T> action);
}
