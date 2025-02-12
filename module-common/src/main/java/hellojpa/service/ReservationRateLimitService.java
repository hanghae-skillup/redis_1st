package hellojpa.service;

import hellojpa.exception.RateLimitExceedException;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReservationRateLimitService {

    private final RedissonClient redissonClient;
    private final StringRedisTemplate redisTemplate;

    private static final String RATE_LIMIT_LUA_SCRIPT =
            "local key = KEYS[1]\n" +
                    "local ttl = tonumber(redis.call('TTL', key))\n" +
                    "if ttl > 0 then\n" +
                    "    return ttl\n" + // TTL이 남아 있으면 반환
                    "end\n" +
                    "redis.call('SET', key, 1, 'EX', 300)\n" + //TTL 설정 (5분)
                    "return 0";

    public void enforceRateLimit(long userId, long screeningId) { // null 차단
        String rateLimitKey = "ratelimit:user:" + userId + ":screening:" + screeningId;

        // Lua 스크립트 실행
        Long ttl = redissonClient.getScript().eval(
                RScript.Mode.READ_WRITE,
                RATE_LIMIT_LUA_SCRIPT,
                RScript.ReturnType.INTEGER,
                Collections.singletonList(rateLimitKey)
        );

        // TTL이 0보다 크다면 요청을 차단
        if (ttl > 0) {
            throw new RateLimitExceedException(
                    "같은 시간대의 영화는 5분에 1번만 예약할 수 있습니다."
            );
        }
    }
}