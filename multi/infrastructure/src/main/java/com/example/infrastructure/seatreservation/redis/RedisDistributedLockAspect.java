package com.example.infrastructure.seatreservation.redis;


import com.example.application.SeatReservation.annotation.DistributedLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RedisDistributedLockAspect {
    private final RedissonClient redissonClient;

    @Around("@annotation(distributedLock)")
    public Object handleDistributedLock(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Throwable {
        String lockKey = distributedLock.key();
        long leaseTime = distributedLock.leaseTime();
        long waitTime = distributedLock.waitTime();

        RLock lock = redissonClient.getLock(lockKey);
        boolean acquired = false;

        try {
            log.info("Trying to acquire lock for key: {}", lockKey);
            acquired = lock.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
            if (!acquired) {
                log.warn("Failed to acquire lock for key: {}", lockKey);
                throw new RuntimeException("Could not acquire lock for key: " + lockKey);
            }

            log.info("Lock acquired for key: {}", lockKey);
            // Proceed with the method execution
            return joinPoint.proceed();
        } finally {
            if (acquired && lock.isHeldByCurrentThread()) {
                log.info("Releasing lock for key: {}", lockKey);
                lock.unlock();
            }
        }
    }
}
