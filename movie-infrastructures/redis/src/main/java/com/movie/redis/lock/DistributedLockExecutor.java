package com.movie.redis.lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
@Slf4j
@RequiredArgsConstructor
public class DistributedLockExecutor {

    private final RedissonClient redissonClient;

    public <T> T executeWithLock(String lockName, Supplier<T> action) {
        RLock lock = redissonClient.getLock(lockName);

        log.info("redisson 락 획득 시도 - 락 이름 : {}", lockName);
        long waitTime = 5L;
        long leaseTime = 5L;
        try {
            if (lock.tryLock(waitTime, leaseTime, TimeUnit.MINUTES)) {
                try {
                    return action.get();
                } finally {
                    if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                        lock.unlock();
                        log.info("redisson 락 해제 - 락 이름 : {}", lockName);
                    }
                }
            } else {
                throw new RuntimeException("Distributed Lock 획득 실패: " + lockName);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Distributed Lock 중단됨: " + lockName, e);
        }
    }

    public void executeWithLock(String lockName, Runnable action) {
        executeWithLock(lockName, () -> {
            action.run();
            return null;
        });
    }
}
