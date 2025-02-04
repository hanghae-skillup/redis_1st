package hanghae.infrastructure.common.functional;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class DistributedLockFunction {

    private final RedissonClient redissonClient;

    public <T> T executeFunctionalLock(String key, long waitSeconds, long leaseSeconds, Supplier<T> supplier) {
        RLock lock = redissonClient.getLock(key);

        try {
            if (lock.tryLock(waitSeconds, leaseSeconds, TimeUnit.SECONDS)) {
                return supplier.get();
            } else {
                throw new IllegalArgumentException("Failed to acquire lock: " + key);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Lock interrupted: " + e.getMessage());
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
