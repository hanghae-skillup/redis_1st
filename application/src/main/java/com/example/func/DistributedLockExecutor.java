package com.example.func;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class DistributedLockExecutor {

    private final RedissonClient redissonClient;

    public <T> T executeWithLock(List<String> lockKeys, long waitTime, long leaseTime, TimeUnit timeUnit, Supplier<T> action) {
        List<RLock> locks = lockKeys.stream()
                .map(redissonClient::getLock)
                .toList();
        boolean allLocked = false;

        try {
            allLocked = redissonClient.getMultiLock(locks.toArray(new RLock[0]))
                    .tryLock(waitTime, leaseTime, timeUnit);

            if (!allLocked) {
                throw new IllegalArgumentException("해당 좌석은 현재 다른 사용자가 예매를 진행하고 있습니다.");
            }

            return action.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (allLocked) {
                locks.forEach(lock -> {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                });
            }
        }
    }
}
