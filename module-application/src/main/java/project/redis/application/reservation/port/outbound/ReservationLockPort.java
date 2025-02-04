package project.redis.application.reservation.port.outbound;

import java.util.List;

public interface ReservationLockPort {
    boolean tryLock(String lockKey, long waitTimeMils, long releaseTimeMils);

    boolean tryScreeningSeatLock(List<String> lockKeys, long waitTimeMils, long releaseTimeMils);

    void releaseLock(String lockKey);

    void releaseMultiLock(List<String> lockKeys);
}
