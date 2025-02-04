package com.movie.redis.ratelimiter;

import com.movie.domain.common.RateLimiterHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RedissonRateLimiterHandler implements RateLimiterHandler {

    private static final int REQUEST_LIMIT_PER_MINUTE = 50;
    private static final int BLOCK_DURATION_HOURS = 1;

    private final RedissonClient redissonClient;
    private final RedisTemplate<String, String> redisTemplate;
    private ValueOperations<String, String> valueOperations;

    @PostConstruct
    public void init() {
        this.valueOperations = redisTemplate.opsForValue();
    }

    /** 요청 횟수 증가 및 초과 여부 확인 */
    public boolean isRequestLimitExceeded(String ip) {
        String requestKey = "rate_limit:%s".formatted(ip);
        Long requestCount = valueOperations.increment(requestKey);

        if (requestCount != null) {
            if (requestCount == 1) {
                redisTemplate.expire(requestKey, 1, TimeUnit.MINUTES);
            }
            return requestCount > REQUEST_LIMIT_PER_MINUTE;
        }
        return false;
    }

    /** IP를 일정 시간 차단 */
    @Override
    public void blockIp(String ip) {
        String blockKey = "blocked_ip:%s".formatted(ip);
        valueOperations.set(blockKey, "blocked", BLOCK_DURATION_HOURS, TimeUnit.HOURS);
    }

    @Override
    public boolean tryAcquire(String key) {
        RRateLimiter rateLimiter = getRateLimiter(key);
        return rateLimiter.tryAcquire(1);
    }

    /** IP가 차단되었는지 확인 */
    @Override
    public boolean isIpBlocked(String ip) {
        String blockKey = "blocked_ip:%s".formatted(ip);
        return Boolean.TRUE.equals(redisTemplate.hasKey(blockKey));
    }

    private RRateLimiter getRateLimiter(String key) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        rateLimiter.trySetRate(RateType.OVERALL, 50, 1, RateIntervalUnit.MINUTES);
        return rateLimiter;
    }

}
