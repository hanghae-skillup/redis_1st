package com.movie.common.ratelimit;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisRateLimiter implements RateLimiter {

    private final RedissonClient redissonClient;

    @Override
    public boolean tryAcquire(String key) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        return rateLimiter.tryAcquire();
    }

    @Override
    public void setRate(String key, int permits, int interval, TimeUnit unit) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        RateIntervalUnit intervalUnit = convertTimeUnit(unit);
        boolean success = rateLimiter.trySetRate(RateType.OVERALL, permits, interval, intervalUnit);
        if (!success) {
            throw new IllegalStateException("Failed to set rate limit for key: " + key);
        }
    }

    private RateIntervalUnit convertTimeUnit(TimeUnit unit) {
        return switch (unit) {
            case SECONDS -> RateIntervalUnit.SECONDS;
            case MINUTES -> RateIntervalUnit.MINUTES;
            case HOURS -> RateIntervalUnit.HOURS;
            case DAYS -> RateIntervalUnit.DAYS;
            default -> throw new IllegalArgumentException("Unsupported time unit: " + unit);
        };
    }
} 