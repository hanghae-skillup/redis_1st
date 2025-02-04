package com.example.app.movie.presentation.service;

import com.example.app.common.exception.RateLimitException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisRateLimitService {

    private final RedisTemplate<String, String> redisTemplate;
    private final static String PREFIX_REQUEST = "MOVIE:REQ:";
    private final static String PREFIX_BLOCK = "MOVIE:BLOCK:";

    public void checkAccessLimit(String clientIp) {
        var requestKey = PREFIX_REQUEST + clientIp;
        var blockKey = PREFIX_BLOCK + clientIp;

        var isBlocked = redisTemplate.hasKey(blockKey);

        if (Boolean.TRUE.equals(isBlocked)) {
            throw new RateLimitException();
        }

        var requestCount = redisTemplate.execute(new DefaultRedisScript<Long>() {
            {
                setScriptText("""
                    local count = redis.call('INCR', KEYS[1])
                    if count == 1 then
                        redis.call('EXPIRE', KEYS[1], 60)
                    end
                    return count
                    """);
                setResultType(Long.class);
            }
        }, Collections.singletonList(requestKey));

        if (requestCount != null && requestCount >= 50) {
            redisTemplate.opsForValue().set(blockKey, "1", 1, TimeUnit.HOURS);
            throw new RateLimitException();
        }
    }
}
