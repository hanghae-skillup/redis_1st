package com.movie.infra.ratelimit;

import com.movie.common.service.RateLimitService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisRateLimitService implements RateLimitService {
    private static final String IP_BAN_PREFIX = "ip:ban:";
    private static final String RATE_LIMIT_PREFIX = "rate:limit:";
    private static final String USER_RESERVATION_PREFIX = "user:reservation:";

    private final RedisTemplate<String, String> redisTemplate;

    public RedisRateLimitService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean checkIpRateLimit(String ip) {
        String key = RATE_LIMIT_PREFIX + ip;
        // 여기에 실제 rate limiting 로직을 구현
        return true; // 임시로 true 반환
    }

    @Override
    public void checkUserReservationRateLimit(Long userId, String scheduleTime) {
        String key = USER_RESERVATION_PREFIX + userId + ":" + scheduleTime;
        // 여기에 실제 user reservation rate limiting 로직을 구현
    }
}