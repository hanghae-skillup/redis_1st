package com.hanghae.infrastructure.adapter.redis;

import com.hanghae.application.enums.ErrorCode;
import com.hanghae.application.exception.CustomRequestException;
import com.hanghae.application.port.out.redis.RedissonLockPort;
import com.hanghae.domain.model.enums.ScreenSeat;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class RedissonLockAdapter implements RedissonLockPort {
    private final RedissonClient redissonClient;

    private String getLockKey(Long scheduleId, ScreenSeat seat) {
        return "reservation:lock:" + scheduleId + ":" + seat;
    }

    /**
     * 하나의 좌석에 대한 락을 실행 및 해제
     */
    @Override
    public <T> T executeWithSeatLock(Long scheduleId, ScreenSeat seat, Supplier<T> action) {
        String lockKey = getLockKey(scheduleId, seat);
        RLock lock = redissonClient.getLock(lockKey);

        try {
            if (lock.tryLock(1, 300, TimeUnit.SECONDS)) { // 1초 대기 후 락 획득, 5분 자동 해제
                try {
                    return action.get();
                } finally {
                    releaseLock(lock);
                }
            } else {
                throw new CustomRequestException("현재 좌석을 다른 사용자가 예매 처리 중입니다." + seat, ErrorCode.SEAT_NOT_AVAILABLE);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("좌석 락 획득 중 인터럽트 발생", e);
        }
    }

    /**
     * 여러개의 좌석에 대한 락을 실행 및 해제
     */
    @Override
    public <T> T executeWithSeatsLocks(Long scheduleId, List<ScreenSeat> seats, Supplier<T> action) {
        List<RLock> locks = seats.stream()
                .map(seat -> redissonClient.getLock(getLockKey(scheduleId, seat)))
                .collect(Collectors.toList());

        try {
            if (tryAcquireLocks(locks)) {
                try {
                    return action.get();
                } finally {
                    releaseLocks(locks);
                }
            } else {
                throw new CustomRequestException("현재 좌석을 다른 사용자가 예매 처리 중입니다." + seats, ErrorCode.SEAT_NOT_AVAILABLE);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("여러 좌석 락 획득 중 인터럽트 발생", e);
        }
    }

    /**
     * 여러 락을 획득하는 메서드
     */
    private boolean tryAcquireLocks(List<RLock> locks) throws InterruptedException {
        for (RLock lock : locks) {
            if (!lock.tryLock(1, 300, TimeUnit.SECONDS)) {
                releaseLocks(locks);
                return false;
            }
        }
        return true;
    }

    /**
     * 단일 락 해제
     */
    private void releaseLock(RLock lock) {
        if (lock.isHeldByCurrentThread()) {
            try {
                lock.unlock();
            } catch (IllegalMonitorStateException e) {
                // 이미 해제된 경우 무시
            }
        }
    }

    /**
     * 여러개의 락 해제
     */
    private void releaseLocks(List<RLock> locks) {
        for (RLock lock : locks) {
            releaseLock(lock);
        }
    }
}
