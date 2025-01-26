package com.example.app.common.function;

import com.example.app.common.asepct.RedisLockTransaction;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class DistributedLockService {

    private final RedissonClient redissonClient;
    private final RedisLockTransaction redisLockTransaction;

    public <T> T executeWithLockAndReturn(Supplier<T> action, String lockKey, long waitTime, long leaseTime) {
        RLock rLock = redissonClient.getLock(lockKey);

        boolean lockAcquired = false;

        try {
            // 락을 획득
            lockAcquired = rLock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);

            if (!lockAcquired) {
                throw new IllegalStateException("락을 획득할 수 없습니다.");
            }

            return redisLockTransaction.execute(action);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            if (lockAcquired) {
                rLock.unlock();
            }
        }
    }
}
