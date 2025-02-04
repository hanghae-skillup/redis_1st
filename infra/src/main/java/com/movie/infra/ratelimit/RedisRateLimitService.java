package com.movie.infra.ratelimit;

import com.movie.common.exception.RateLimitExceededException;
import com.movie.common.service.RateLimitService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Profile("prod")
@RequiredArgsConstructor
public class RedisRateLimitService implements RateLimitService {
    private static final String IP_BAN_KEY_PREFIX = "ip:ban:";
    private static final String IP_RATE_LIMIT_KEY_PREFIX = "ip:rate:";
    private static final String USER_RESERVATION_RATE_LIMIT_KEY_PREFIX = "user:reservation:rate:";
    private static final Duration BAN_DURATION = Duration.ofHours(1);
    private static final int MAX_REQUESTS_PER_MINUTE = 50;

    private final RedissonClient redissonClient;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void checkIpRateLimit(String ip) {
        String banKey = IP_BAN_KEY_PREFIX + ip;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(banKey))) {
            throw new RateLimitExceededException("IP가 차단되었습니다. 1시간 후에 다시 시도해주세요.");
        }

        String rateLimitKey = IP_RATE_LIMIT_KEY_PREFIX + ip;
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(rateLimitKey);
        rateLimiter.trySetRate(RateType.OVERALL, MAX_REQUESTS_PER_MINUTE, 1, RateIntervalUnit.MINUTES);

        if (!rateLimiter.tryAcquire()) {
            redisTemplate.opsForValue().set(banKey, "banned", BAN_DURATION);
            throw new RateLimitExceededException("너무 많은 요청을 보냈습니다. IP가 1시간 동안 차단됩니다.");
        }
    }

    @Override
    public void checkUserReservationRateLimit(Long userId, String scheduleTime) {
        String key = USER_RESERVATION_RATE_LIMIT_KEY_PREFIX + userId + ":" + scheduleTime;
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        rateLimiter.trySetRate(RateType.OVERALL, 1, 5, RateIntervalUnit.MINUTES);

        if (!rateLimiter.tryAcquire()) {
            throw new RateLimitExceededException("예매는 5분에 한 번만 시도할 수 있습니다.");
        }
    }
}