package com.example.app.common.asepct;

import com.example.app.common.annotation.DistributedLock;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

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

        // 락
        RLock rLock = redissonClient.getLock(lockKey);

        boolean lockAcquired = false;

        try {
            // 락을 획득하려 시도
            lockAcquired = rLock.tryLock(waitTime, leaseTime, timeUnit);

            if (!lockAcquired) {
                throw new IllegalStateException("락을 획득할 수 없습니다.");
            }

            return redisLockTransaction.proceed(joinPoint);
        } finally {
            if (lockAcquired) {
                rLock.unlock();  // 메소드 실행 후 락 해제
            }
        }
    }
}
