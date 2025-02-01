package com.hanghae.application.port.out.redis;

public interface RedisRateLimitPort {
    boolean isAllowed(String ip);
    boolean canReserve(Long scheduleId, Long memberId);
}
