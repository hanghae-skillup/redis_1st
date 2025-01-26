
package com.example.infrastructure.seatreservation;

import com.example.application.SeatReservation.redis.DistributedLockManager;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisDistributedLockManager  implements DistributedLockManager {

    private final RedissonClient redissonClient;

    @Override
    public <T> T executeWithLock(String key, long leaseTime, long waitTime, DistributedLockManager.LockCallback<T> callback) throws Exception {
        RLock lock = redissonClient.getLock(key);
        boolean acquired = false;

        try {
            acquired = lock.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);

            if (!acquired) {
                log.warn("Failed to acquire lock for key: {}", key);
                throw new RuntimeException("Could not acquire lock for key: " + key);
            }

            return callback.execute();
        } catch (Exception e) {
            log.error("Error while executing with lock for key: {}", key, e);
            throw e;
        } finally {
            if (acquired && lock.isHeldByCurrentThread()) {
                log.info("Releasing lock: {}", key);
                lock.unlock();

            }
        }
    }

    @FunctionalInterface
    public interface LockCallback<T> {
        T execute() throws Exception;
    }
}

