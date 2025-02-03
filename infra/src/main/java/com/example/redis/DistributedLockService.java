package com.example.redis;

import lombok.RequiredArgsConstructor;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DistributedLockService {
    private final RedissonClient redissonClient;

    private final RedisLockTransaction redisLockTransaction;

    public <T> T executeWithLockAndReturn(Supplier<T> action, Long screeningId, List<String> seatIds, long waitTime, long leaseTime) throws InterruptedException {

        List<RLock> locks = seatIds.stream()
                .map(seatId -> redissonClient.getLock("lock:screening:" + screeningId + ":seat:" + seatId))
                .collect(Collectors.toList());

        RLock multiLock = new RedissonMultiLock(locks.toArray(new RLock[0]));

        boolean lockAcquired = false;

        try {
            lockAcquired = multiLock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);

            if (!lockAcquired) {
                throw new IllegalStateException("락을 획득할 수 없습니다.");
            }

            return redisLockTransaction.execute(action);

        } finally {
            if (lockAcquired) {
                multiLock.unlock();  // 모든 락 해제
            }
        }
    }

}
