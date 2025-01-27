package com.example.app.common.function;

import com.example.app.common.asepct.RedisLockTransaction;
import com.example.app.common.exception.LockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class DistributedLockService {

    private final RedissonClient redissonClient;
    private final RedisLockTransaction redisLockTransaction;

    public <T> T executeWithLockAndReturn(Supplier<T> action, String lockKey, long waitTime, long leaseTime) {
        RLock rLock = redissonClient.getLock(lockKey);

        try {
            boolean lockAcquired = rLock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);

            if (!lockAcquired) {
                log.error("락을 획득 실패");
                throw new LockException();
            }

            return redisLockTransaction.execute(action);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
    }
}
