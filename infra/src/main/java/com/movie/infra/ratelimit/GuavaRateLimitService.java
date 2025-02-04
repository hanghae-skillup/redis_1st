package com.movie.infra.ratelimit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import com.movie.common.exception.RateLimitExceededException;
import com.movie.common.service.RateLimitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Profile("!prod")
public class GuavaRateLimitService implements RateLimitService {
    private final Cache<String, RateLimiter> rateLimiters;
    private final Cache<String, Integer> requestCounts;
    private final Cache<String, RateLimiter> reservationRateLimiters;
    private final int maxRequestsPerMinute = 50;

    public GuavaRateLimitService() {
        this.rateLimiters = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.HOURS)
                .build();

        this.requestCounts = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build();

        this.reservationRateLimiters = CacheBuilder.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public void checkIpRateLimit(String ip) {
        Integer count = requestCounts.getIfPresent(ip);
        if (count != null && count >= maxRequestsPerMinute) {
            throw new RateLimitExceededException("IP가 차단되었습니다. 1시간 후에 다시 시도해주세요.");
        }

        RateLimiter limiter = rateLimiters.getIfPresent(ip);
        if (limiter == null) {
            limiter = RateLimiter.create(maxRequestsPerMinute / 60.0); // 초당 요청 수로 변환
            rateLimiters.put(ip, limiter);
        }

        if (!limiter.tryAcquire()) {
            Integer newCount = count == null ? 1 : count + 1;
            requestCounts.put(ip, newCount);
            
            if (newCount >= maxRequestsPerMinute) {
                rateLimiters.put(ip, RateLimiter.create(0.0)); // 1시간 동안 차단
                throw new RateLimitExceededException("너무 많은 요청을 보냈습니다. IP가 1시간 동안 차단됩니다.");
            }
            
            throw new RateLimitExceededException("너무 많은 요청을 보냈습니다. 잠시 후 다시 시도해주세요.");
        }
    }

    @Override
    public void checkUserReservationRateLimit(Long userId, String scheduleTime) {
        String key = userId + ":" + scheduleTime;
        RateLimiter limiter = reservationRateLimiters.getIfPresent(key);
        if (limiter == null) {
            limiter = RateLimiter.create(1.0 / 300.0); // 5분에 1번
            reservationRateLimiters.put(key, limiter);
        }

        if (!limiter.tryAcquire()) {
            throw new RateLimitExceededException("예매는 5분에 한 번만 시도할 수 있습니다.");
        }
    }
} 