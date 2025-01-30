package com.movie.redis.lock;

import com.movie.common.exception.ApplicationException;
import com.movie.common.exception.ErrorCode;
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

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class DistributedLockAspect {

    private final RedissonClient redissonClient;

    @Around("@annotation(com.movie.redis.lock.DistributedLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        Object[] args = joinPoint.getArgs();

        String lockName = distributedLock.lockName();
        String generatedLockName = generateLockKey(lockName, args);

        RLock rLock = redissonClient.getLock(generatedLockName);

        log.info("redisson 락 획득 - 락 이름 : {}", generatedLockName);

        long waitTime = distributedLock.waitTime();
        long leaseTime = distributedLock.leaseTime();
        TimeUnit timeUnit = distributedLock.timeUnit();

        try {
            boolean isLocked = rLock.tryLock(waitTime, leaseTime, timeUnit);
            if (!isLocked) {
                throw new ApplicationException(ErrorCode.DISTRIBUTED_LOCK_NOT_AVAILABLE);
            }
            return joinPoint.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            if (rLock.isHeldByCurrentThread()) {
                try {
                    rLock.unlock();
                    log.info("redisson 락 해제 - 락 이름 : {}", generatedLockName);
                } catch (Exception e) {
                    log.error("redisson 락 해제 중 예외 발생 - 락 이름 : {}", generatedLockName, e);
                }
            }
        }
    }

    private String generateLockKey(String lockName, Object[] args) {
        if (args.length > 0) {
            Object firstArg = args[0];
            return lockName + ":" + firstArg.toString();
        }
        return lockName;
    }

}
