package com.example.app.common.asepct;

import com.example.app.common.annotation.DistributedLock;
import com.example.app.common.exception.LockException;
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
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAspect {

    private final RedissonClient redissonClient;
    private final RedisLockTransaction redisLockTransaction;

    @Around("@annotation(com.example.app.common.annotation.DistributedLock)")
    public Object doLock(final ProceedingJoinPoint joinPoint) throws Throwable {
        // annotation 획득
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        // annotation 설정
        String lockKey = distributedLock.key();
        long leaseTime = distributedLock.leaseTime();
        long waitTime = distributedLock.waitTime();
        TimeUnit timeUnit = distributedLock.timeUnit();

        RLock rLock = redissonClient.getLock(lockKey);

        try {
            boolean lockAcquired = rLock.tryLock(waitTime, leaseTime, timeUnit);

            if (!lockAcquired) {
                log.error("락을 획득 실패");
                throw new LockException();
            }

            return redisLockTransaction.proceed(joinPoint);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
    }
}
