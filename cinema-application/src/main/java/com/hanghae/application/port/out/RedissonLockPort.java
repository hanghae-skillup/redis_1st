package com.hanghae.application.port.out;

import com.hanghae.domain.model.enums.ScreenSeat;

import java.util.List;

public interface RedissonLockPort {
    boolean tryLockSeat(Long scheduleId, ScreenSeat seat); // 하나의 좌석 락 획득
    void unlockSeat(Long scheduleId, ScreenSeat seat); // 하나의 좌석 락 해제
    boolean tryLockSeats(Long scheduleId, List<ScreenSeat> seats); //여러 좌석을 동시에 락 획득
    void unlockSeats(Long scheduleId, List<ScreenSeat> seats); // 여러 좌석을 동시에 락 해제
}
