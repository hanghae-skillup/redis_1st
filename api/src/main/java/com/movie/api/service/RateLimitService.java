package com.movie.api.service;

import com.movie.api.exception.RateLimitExceededException;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateLimitService {

    private final RedissonClient redissonClient;

    public void checkIpRateLimit(String ip) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter("ip-rate-limit:" + ip);
        rateLimiter.trySetRate(RateType.OVERALL, 50, 1, RateIntervalUnit.MINUTES);

        if (!rateLimiter.tryAcquire()) {
            throw new RateLimitExceededException("Too many requests from IP: " + ip);
        }
    }

    public void checkUserReservationRateLimit(Long userId, String scheduleTime) {
        String key = "user-reservation-rate-limit:" + userId + ":" + scheduleTime;
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        rateLimiter.trySetRate(RateType.OVERALL, 1, 5, RateIntervalUnit.MINUTES);

        if (!rateLimiter.tryAcquire()) {
            throw new RateLimitExceededException("Too many reservation attempts for this schedule. Please wait 5 minutes.");
        }
    }
} 