package com.movie.infra.ratelimit;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Profile;

import java.util.concurrent.TimeUnit;

@Service
@Profile("!test")
public class ReservationRateLimitService {

    private static final String RATE_LIMITER_KEY_PREFIX = "reservation:rate:limiter:";
    private static final int RATE_LIMIT = 1;
    private static final int RATE_INTERVAL = 5;

    private final RedissonClient redissonClient;
    private final RedisTemplate<String, String> redisTemplate;

    public ReservationRateLimitService(RedissonClient redissonClient, RedisTemplate<String, String> redisTemplate) {
        this.redissonClient = redissonClient;
        this.redisTemplate = redisTemplate;
    }

    public boolean canBook(String userId, String scheduleId) {
        String key = RATE_LIMITER_KEY_PREFIX + userId + ":" + scheduleId;
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        rateLimiter.trySetRate(RateType.OVERALL, RATE_LIMIT, RATE_INTERVAL, RateIntervalUnit.MINUTES);
        
        boolean acquired = rateLimiter.tryAcquire();
        if (acquired) {
            redisTemplate.opsForValue().set(key + ":last_attempt", String.valueOf(System.currentTimeMillis()),
                    RATE_INTERVAL, TimeUnit.MINUTES);
        }
        
        return acquired;
    }
} 