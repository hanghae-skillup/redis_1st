package com.movie.infra.ratelimit;

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
public class RateLimitService {
    // 1시간(ms) 동안 차단
    private static final long BAN_DURATION_MS = 3600_000;
    // 1분당 50회 요청 제한 (초당 약 0.83회)
    private static final int REQUESTS_PER_MINUTE = 50;
    private static final String RATE_LIMITER_KEY_PREFIX = "rate:limiter:";
    private static final String BAN_KEY_PREFIX = "ban:";

    private final RedissonClient redissonClient;
    private final RedisTemplate<String, String> redisTemplate;

    public RateLimitService(RedissonClient redissonClient, RedisTemplate<String, String> redisTemplate) {
        this.redissonClient = redissonClient;
        this.redisTemplate = redisTemplate;
    }

    private RRateLimiter createNewBucket(String ip) {
        String key = RATE_LIMITER_KEY_PREFIX + ip;
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        rateLimiter.trySetRate(RateType.OVERALL, REQUESTS_PER_MINUTE, 1, RateIntervalUnit.MINUTES);
        return rateLimiter;
    }

    public boolean isBanned(String ip) {
        String banKey = BAN_KEY_PREFIX + ip;
        String bannedUntil = redisTemplate.opsForValue().get(banKey);
        
        if (bannedUntil != null) {
            long banExpiry = Long.parseLong(bannedUntil);
            if (System.currentTimeMillis() < banExpiry) {
                return true;
            }
            redisTemplate.delete(banKey);
        }
        return false;
    }

    public boolean tryAcquire(String ip) {
        if (isBanned(ip)) {
            return false;
        }

        RRateLimiter rateLimiter = createNewBucket(ip);
        boolean acquired = rateLimiter.tryAcquire();

        if (!acquired) {
            String banKey = BAN_KEY_PREFIX + ip;
            long banUntil = System.currentTimeMillis() + BAN_DURATION_MS;
            redisTemplate.opsForValue().set(banKey, String.valueOf(banUntil), BAN_DURATION_MS, TimeUnit.MILLISECONDS);
        }

        return acquired;
    }
} 