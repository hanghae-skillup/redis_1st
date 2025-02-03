package org.example.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@AllArgsConstructor
public class RedisRateLimiterService {
    private final StringRedisTemplate redisTemplate;

    private static final String LUA_SCRIPT =
            "local ip = KEYS[1]\n" +
                    "local blockKey = 'block:' .. ip\n" +
                    "local requestKey = 'request:' .. ip\n" +

                    "if redis.call('EXISTS', blockKey) == 1 then\n" +
                    "    return -1\n" +
                    "end\n" +

                    "local currentCount = tonumber(redis.call('GET', requestKey) or '0')\n" +
                    "redis.call('SET', requestKey, currentCount) \n" +

                    "if currentCount >= tonumber('50') then\n" +
                    "    redis.call('SETEX', blockKey, 3600, 'BLOCKED')\n" +
                    "    redis.call('DEL', requestKey)\n" +
                    "    return -1\n" +
                    "end\n" +

                    "currentCount = redis.call('INCR', requestKey)\n" +

                    "return currentCount";

    public Long isAllowed(String ip) {
        Long result = redisTemplate.execute(RedisScript.of(LUA_SCRIPT, Long.class), Collections.singletonList(ip));
        return result;
    }
}
