package hanghae.infrastructure.adapter;

import hanghae.domain.port.ReservationRateLimitChecker;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ReservationRateLimitCheckerAdapter implements ReservationRateLimitChecker {

    private final RedissonClient redissonClient;

    private final static String RESERVATION_LIMIT_MAP = "reservation_limit_map";

    @Override
    public void oneReservationPerFiveMinutes(String key) {
        RMapCache<String, Boolean> reservationMap = redissonClient.getMapCache(RESERVATION_LIMIT_MAP);

        if (reservationMap.containsKey(key)) {
            long remainTtl = getTtl(key, reservationMap) / 1000;
            throw new IllegalArgumentException("5분 동안 동일 상영 시간 예매가 불가능합니다. 남은 시간: " + remainTtl + "s");
        }

        reservationMap.put(key, true, 300, TimeUnit.SECONDS);
    }

    private long getTtl(String key, RMapCache<String, Boolean> reservationMap) {
        return reservationMap.remainTimeToLive(key);
    }
}
