package org.example.config;

import lombok.AllArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
@AllArgsConstructor
public class RedissonLockUtil {
    private final RedissonClient redissonClient;

    /**
     * 분산락을 적용하여 함수 실행
     * @param lockKeys 락 키 값 (예: "lock:seat:1:A:5")
     * @param waitTime 락 대기 시간 (초)
     * @param leaseTime 락 유지 시간 (초)
     * @param task 락을 걸고 실행할 함수
     * @return task 실행 결과
     */
    public <T> T executeWithMultiLock(List<String> lockKeys, int waitTime, int leaseTime, Supplier<T> task) {
        List<RLock> locks = lockKeys.stream()
                .map(redissonClient::getLock)
                .toList();
        try {
            boolean locked = redissonClient.getMultiLock(locks.toArray(new RLock[0]))
                    .tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
            if (!locked) {
                throw new RuntimeException("락 획득 실패: " + lockKeys);
            }
            return task.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("락 처리 중 인터럽트 발생", e);
        } finally {
            for (RLock lock : locks) {
                if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }
}
