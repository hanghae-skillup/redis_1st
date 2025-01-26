package com.hanghae.infrastructure.adapter;

import com.hanghae.application.port.out.RedissonLockPort;
import com.hanghae.domain.model.enums.ScreenSeat;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedissonLockAdapter implements RedissonLockPort {
    private final RedissonClient redissonClient;

    @Override
    public boolean tryLockSeat(Long scheduleId, ScreenSeat seat) {
        String lockKey = "reservation:lock:" + scheduleId + ":" + seat;
        RLock lock = redissonClient.getLock(lockKey);

        try {
            return lock.tryLock(5, 10, TimeUnit.SECONDS); // 최대 5초 대기, 10초 후 자동 해제
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    @Override
    public void unlockSeat(Long scheduleId, ScreenSeat seat) {
        String lockKey = "reservation:lock:" + scheduleId + ":" + seat;
        RLock lock = redissonClient.getLock(lockKey);
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    /* 여러 좌석에 대해 락 획득 */
    @Override
    public boolean tryLockSeats(Long scheduleId, List<ScreenSeat> seats) {
        List<RLock> acquiredLocks = new ArrayList<>();
        try {
            for (ScreenSeat seat : seats) {
                String lockKey = "reservation:lock:" + scheduleId + ":" + seat;
                RLock lock = redissonClient.getLock(lockKey);
                boolean locked = lock.tryLock(5, 10, TimeUnit.SECONDS);
                if (!locked) {
                    //락 획득 실패 시 이미 획득한 락들을 해제
                    releaseLocks(acquiredLocks);
                    return false;
                }
                acquiredLocks.add(lock);
            }
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // 예외 발생 시 이미 획득한 락들을 해제
            releaseLocks(acquiredLocks);
            return false;
        }
    }

    /* 여러 좌석의 락 해제 */
    @Override
    public void unlockSeats(Long scheduleId, List<ScreenSeat> seats) {
        for (ScreenSeat seat : seats) {
            String lockKey = "reservation:lock:" + scheduleId + ":" + seat;
            RLock lock = redissonClient.getLock(lockKey);
            if (lock.isHeldByCurrentThread()) {
                try {
                    lock.unlock();
                } catch (IllegalMonitorStateException e) {
                    // 이미 해제된 락을 다시 해제하려 하면 예외가 발생할 수 있으므로 무시
                }
            }
        }
    }

    /* 다수 락 해제 */
    private void releaseLocks(List<RLock> acquiredLocks) {
        for (RLock lock : acquiredLocks) {
            if (lock.isHeldByCurrentThread()) {
                try {
                    lock.unlock();
                } catch (IllegalMonitorStateException e) {
                    // 이미 해제된 락이 있으면 무시
                }
            }
        }
    }
}
