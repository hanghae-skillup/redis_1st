package com.hanghae.application.port.out.redis;

public interface RedisRateLimitPort {
    boolean isAllowed(String ip);
    boolean tryReserveWithLimit(Long scheduleId, Long memberId);
    boolean canReserve(Long scheduleId, Long memberId);
    void setReservationLimit(Long scheduleId, Long memberId);
}
