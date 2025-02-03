package com.movie.infra.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitService {
    // 1시간(ms) 동안 차단
    private static final long BAN_DURATION_MS = 3600_000;
    // 1분당 50회 요청 제한 (초당 약 0.83회)
    private static final double RATE_PER_SECOND = 50.0 / 60.0;

    private final ConcurrentHashMap<String, RateLimiter> limiters = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> bannedIps = new ConcurrentHashMap<>();

    public boolean isBanned(String ip) {
        Long bannedUntil = bannedIps.get(ip);
        if (bannedUntil != null) {
            if (System.currentTimeMillis() < bannedUntil) {
                return true;
            }
            bannedIps.remove(ip);
        }
        return false;
    }

    public boolean tryAcquire(String ip) {
        if (isBanned(ip)) {
            return false;
        }
        RateLimiter limiter = limiters.computeIfAbsent(ip, 
            k -> RateLimiter.create(RATE_PER_SECOND));
        boolean acquired = limiter.tryAcquire();
        if (!acquired) {
            bannedIps.put(ip, System.currentTimeMillis() + BAN_DURATION_MS);
        }
        return acquired;
    }
} 