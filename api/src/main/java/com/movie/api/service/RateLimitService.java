package com.movie.api.service;

import com.movie.api.ratelimit.RateLimiter;
import com.movie.common.exception.RateLimitExceededException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RateLimitService {

    private final RateLimiter rateLimiter;
    private static final String IP_RATE_LIMIT_KEY_PREFIX = "ip-rate-limit:";
    private static final String USER_RESERVATION_RATE_LIMIT_KEY_PREFIX = "user-reservation-rate-limit:";

    public void checkIpRateLimit(String ip) {
        String key = IP_RATE_LIMIT_KEY_PREFIX + ip;
        rateLimiter.setRate(key, 50, 1, TimeUnit.MINUTES);

        if (!rateLimiter.tryAcquire(key)) {
            throw new RateLimitExceededException("Too many requests from IP: " + ip);
        }
    }

    public void checkUserReservationRateLimit(Long userId, String scheduleTime) {
        String key = USER_RESERVATION_RATE_LIMIT_KEY_PREFIX + userId + ":" + scheduleTime;
        rateLimiter.setRate(key, 1, 5, TimeUnit.MINUTES);

        if (!rateLimiter.tryAcquire(key)) {
            throw new RateLimitExceededException("Too many reservation attempts for this schedule. Please wait 5 minutes.");
        }
    }
} 