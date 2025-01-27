package com.example.application.lock;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
public class RedissonDistributedLockExecutor implements DistributedLockExecutor {

    private final RedissonClient redissonClient;

    public RedissonDistributedLockExecutor(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public <T> T executeWithLock(String key, long waitTime, long leaseTime, Supplier<T> task) {
        RLock lock = redissonClient.getLock(key);
        boolean acquired = false;

        try {
            acquired = lock.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
            if (!acquired) {
                throw new IllegalStateException("[ERROR] Unable to acquire lock for key: " + key);
            }
            return task.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("[ERROR] Lock acquisition interrupted", e);
        } finally {
            if (acquired) {
                lock.unlock();
            }
        }
    }

}
