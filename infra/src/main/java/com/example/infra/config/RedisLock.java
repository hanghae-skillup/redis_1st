package com.example.infra.config;

import com.example.common.util.BusinessException;
import com.example.common.util.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;


@Component
@RequiredArgsConstructor
public class RedisLock {

    private final RedisTemplate<String, String> redisTemplate;

    public <T> T executeWithLock(String key, long leaseTime, long waitTime, Supplier<T> action) {
        String lockValue = UUID.randomUUID().toString();
        long startTime = System.currentTimeMillis();

        try {
            while (System.currentTimeMillis() - startTime < waitTime) {
                if (tryLock(key, lockValue, leaseTime)) {
                    try {
                        return action.get(); // 비즈니스 로직 실행
                    } finally {
                        unlock(key, lockValue); // 락 해제
                    }
                }
                Thread.sleep(50); // 대기 간격
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new BusinessException("락 획득 중 인터럽트가 발생했습니다.", ErrorCode.INTERNAL_SERVER_ERROR);
        }

        throw new BusinessException("현재 작업이 진행 중입니다. 잠시 후 다시 시도해주세요.",ErrorCode.LOCK_TIMEOUT);
    }

    private boolean tryLock(String key, String value, long leaseTime) {
        Boolean success = redisTemplate.opsForValue().setIfAbsent(key, value, leaseTime, TimeUnit.MILLISECONDS);
        return Boolean.TRUE.equals(success);
    }

    public void unlock(String key,String value) {
        String luaScript = """
            if redis.call("get", KEYS[1]) == ARGV[1] then
                return redis.call("del", KEYS[1])
            else
                return 0
            end
            """;

        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        try {
            Boolean success = connection.eval(
                    luaScript.getBytes(),
                    ReturnType.BOOLEAN,
                    1,
                    key.getBytes(),
                    value.getBytes()
            );
            if (!Boolean.TRUE.equals(success)) {
                throw new BusinessException("락 해제 실패: 락 소유자와 일치하지 않습니다.", ErrorCode.LOCK_RELEASE_FAILED);
            }
        } finally {
            connection.close();
        }
    }
}
