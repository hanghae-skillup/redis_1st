package project.redis.infrastructure.reservation.adapter;

import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import project.redis.application.reservation.port.outbound.ReservationLockPort;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationLockAdapter implements ReservationLockPort {

    private final RedissonClient redissonClient;

    @Override
    public boolean tryLock(String lockKey, long waitTimeMils, long releaseTimeMils) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTimeMils, releaseTimeMils, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    @Override
    public boolean tryScreeningSeatLock(List<String> lockKeys, long waitTimeMils, long releaseTimeMils) {
        RLock[] locks = lockKeys.stream()
                .map(redissonClient::getLock)
                .toArray(RLock[]::new);

        RLock multiLock = redissonClient.getMultiLock(locks);

        try {
            log.info("Trying to acquire multi-lock for keys: {}", lockKeys);
            boolean acquired = multiLock.tryLock(waitTimeMils, releaseTimeMils, TimeUnit.MILLISECONDS);
            log.info("Multi-lock acquired: {}", acquired);
            return acquired;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    @Override
    public void releaseLock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        if (lock.isHeldByCurrentThread()) {
            log.info("Release lock ...{}", lockKey);
            lock.unlock();
        }
    }

    @Override
    public void releaseMultiLock(List<String> lockKeys) {
        lockKeys.forEach(lockKey -> {
            RLock lock = redissonClient.getLock(lockKey);
            if (lock.isHeldByCurrentThread()) {
                log.info("Release lock ...{}", lockKey);
                lock.unlock();
            }
        });
    }
}
