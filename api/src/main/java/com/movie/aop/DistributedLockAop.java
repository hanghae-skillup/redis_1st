package com.movie.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class DistributedLockAop {

    private final RedissonClient redissonClient;

    @Around("@annotation(com.movie.aop.DistributedLock)")
    public Object executeWithLock(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        String key = distributedLock.key();
        RLock lock = redissonClient.getLock(key);

        try {
            boolean isLocked = lock.tryLock(
                    distributedLock.waitTime(),
                    distributedLock.leaseTime(),
                    distributedLock.timeUnit()
            );

            if (!isLocked) {
                throw new IllegalStateException("Failed to acquire distributed lock");
            }

            return joinPoint.proceed();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Lock acquisition was interrupted", e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
} 