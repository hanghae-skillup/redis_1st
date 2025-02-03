package com.movie.domain.common;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RateLimitRedisRepository {

    private static final int REQUEST_LIMIT_PER_MINUTE = 50;
    private static final int BLOCK_DURATION_HOURS = 1;

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
    public void blockIp(String ip) {
        String blockKey = "blocked_ip:%s".formatted(ip);
        valueOperations.set(blockKey, "blocked", BLOCK_DURATION_HOURS, TimeUnit.HOURS);
    }

    /** IP가 차단되었는지 확인 */
    public boolean isIpBlocked(String ip) {
        String blockKey = "blocked_ip:%s".formatted(ip);
        return Boolean.TRUE.equals(redisTemplate.hasKey(blockKey));
    }

}
