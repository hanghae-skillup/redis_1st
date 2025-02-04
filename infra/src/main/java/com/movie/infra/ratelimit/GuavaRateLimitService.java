package com.movie.infra.ratelimit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class GuavaRateLimitService implements RateLimitService {
    private final Cache<String, RateLimiter> rateLimiters;
    private final Cache<String, Integer> requestCounts;
    private final int maxRequestsPerMinute = 50;
    private final int blockDurationHours = 1;

    public GuavaRateLimitService() {
        this.rateLimiters = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.HOURS)
                .build();

        this.requestCounts = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public boolean isLimited(String key) {
        Integer count = requestCounts.getIfPresent(key);
        if (count != null && count >= maxRequestsPerMinute) {
            return true;
        }

        RateLimiter limiter = rateLimiters.getIfPresent(key);
        return limiter != null && !limiter.tryAcquire();
    }

    @Override
    public void increment(String key) {
        Integer count = requestCounts.getIfPresent(key);
        if (count == null) {
            count = 0;
        }
        
        requestCounts.put(key, count + 1);
        
        if (count + 1 >= maxRequestsPerMinute) {
            // 1분 내 50회 초과 시 1시간 동안 차단
            rateLimiters.put(key, RateLimiter.create(0.0)); // 0.0은 모든 요청을 차단
        }
    }

    @Override
    public void reset(String key) {
        rateLimiters.invalidate(key);
        requestCounts.invalidate(key);
    }
} 