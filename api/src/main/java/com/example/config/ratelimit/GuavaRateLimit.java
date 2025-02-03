package com.example.config.ratelimit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class GuavaRateLimit implements RateLimit {

    private static final double PERMITS_PER_SECOND = 2.0;
    private static final int MAX_REQUEST_PER_MINUTE = 50;

    private final Cache<String, Boolean> blockedIpCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();

    private final Cache<String, Integer> requestCountCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build();

    private final Cache<String, RateLimiter> ipRateLimiterCache = CacheBuilder.newBuilder()
            .expireAfterAccess(1, TimeUnit.MINUTES)
            .build();

    private final Cache<String, Long> reservationCache = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES) // 5분 동안 캐시 유지
            .build();

    @Override
    public boolean isMovieSearchAllowed(String ip) {
        if (isBlocked(ip)) {
            return false;
        }

        RateLimiter rateLimiter = getRateLimiter(ip);
        if (!rateLimiter.tryAcquire()) {
            return false;
        }

        int count = incrementRequestCount(ip);
        if (count > MAX_REQUEST_PER_MINUTE) {
            blockIp(ip);
            return false;
        }

        return true;
    }

    @Override
    public boolean isReservationAllowed(Long memberId, Long screeningId) {

        String key = memberId + "_" + screeningId;

        // null이면 예약 내역이 없다는 거니까
        // 데이터를 만들어줘야지
        if (reservationCache.getIfPresent(key) == null) {
            reservationCache.put(key, System.currentTimeMillis());
            return true;
        }

        return false;
    }

    private boolean isBlocked(String ip) {
        return blockedIpCache.getIfPresent(ip) != null;
    }

    private void blockIp(String ip) {
        blockedIpCache.put(ip, Boolean.TRUE);
    }

    private RateLimiter getRateLimiter(String ip) {
        RateLimiter rateLimiter = ipRateLimiterCache.getIfPresent(ip);
        if (rateLimiter == null) {
            rateLimiter = RateLimiter.create(PERMITS_PER_SECOND);
            ipRateLimiterCache.put(ip, rateLimiter);
        }
        return rateLimiter;
    }

    private int incrementRequestCount(String ip) {
        Integer count = requestCountCache.getIfPresent(ip);
        if (count == null) {
            count = 0;
        }
        count++;
        requestCountCache.put(ip, count);
        return count;
    }
}
