package com.example.aop;

import com.example.reservation.request.ReservationServiceRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAspect {

    private final RedissonClient redissonClient;

    @Around("@annotation(distributedLock)")
    public Object around(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Throwable {

        List<String> lockKeys = getLockKeys(joinPoint, distributedLock);

        List<RLock> locks = lockKeys.stream()
                .map(redissonClient::getLock)
                .toList();

        boolean allLocked = false;

        try {
            allLocked = redissonClient.getMultiLock(locks.toArray(new RLock[0]))
                    .tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());

            if (!allLocked) {
                throw new IllegalArgumentException("해당 좌석은 현재 다른 사용자가 예매를 진행하고 있습니다.");
            }
            return joinPoint.proceed();
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

    private List<String> getLockKeys(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof ReservationServiceRequest request) {
                return request.getSeatIds().stream()
                        .map(seatId -> "reservation:screening:" + request.getScreeningId() + ":seat:" + seatId)
                        .toList();
            }
        }
        throw new IllegalArgumentException("예매할 좌석 정보가 없습니다.");
    }
}
