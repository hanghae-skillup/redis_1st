package com.example.app.booking.presentation.service;

import com.example.app.common.exception.RateLimitException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RedisRateLimitService {

    private final RedisTemplate<String, String> redisTemplate;

    public void checkAccessLimit(String key) {
        Boolean allowed = redisTemplate.execute(new DefaultRedisScript<Boolean>() {
            {
                setScriptText("""
                    local key = KEYS[1]
                    local now = redis.call('TIME')
                    local timestamp = tonumber(now[1])
                    
                    if redis.call('GET', key) then
                        return 0
                    end

                    return 1
                    """);
                setResultType(Boolean.class);
            }
        }, Collections.singletonList(key));

        if (Boolean.FALSE.equals(allowed)) {
            throw new RateLimitException();
        }
    }

    public void setAccessLimit(String key) {
        redisTemplate.execute(new DefaultRedisScript<Boolean>() {
            {
                setScriptText("""
                    local key = KEYS[1]
                    local now = redis.call('TIME')
                    local timestamp = tonumber(now[1])
                    
                    if redis.call('GET', key) then
                        return
                    end
                    
                    redis.call('SET', key, timestamp, 'EX', 300)  -- 5분 만료
                    """);
                setResultType(Boolean.class);
            }
        }, Collections.singletonList(key));
    }
}
