package hanghae.infrastructure.common.aop;

import hanghae.infrastructure.common.annotation.DistributedLock;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAspect {

    private final RedissonClient redissonClient;

    @Around("@annotation(distributedLock)")
    public Object around(ProceedingJoinPoint point, DistributedLock distributedLock) throws Throwable {
        String key = distributedLock.key();
        long waitTime = distributedLock.waitSeconds();
        long leaseTime = distributedLock.leaseSeconds();

        RLock lock = redissonClient.getLock(key);

        if (lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS)) {
            try {
                return point.proceed();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        } else {
            throw new IllegalArgumentException("Failed to acquire lock: " + key);
        }
    }

}
