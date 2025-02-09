package com.movie.redis.cache;

import com.movie.domain.movie.ReservationRedisRepository;
import com.movie.domain.movie.dto.command.ReservationCommand;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReservationRedisRepositoryImpl implements ReservationRedisRepository {

    private static final String KEY_FORMAT = "s_%d:u_%d:reservedAt";
    private final RedisTemplate<String, String> redisTemplate;
    private ValueOperations<String, String> valueOperations;

    @PostConstruct
    public void init() {
        this.valueOperations = redisTemplate.opsForValue();
    }

    public void saveReservationDone(ReservationCommand.CreateCache cacheData) {
        if (cacheData.scheduleId() == null || cacheData.userId() == null || cacheData.reservedAt() == null) {
            log.error("keys must not be null, scheduleId, userId, reservedAt");
            return;
        }
        try {
            String key = KEY_FORMAT.formatted(cacheData.scheduleId(), cacheData.userId());

            long reservedTimeMillis = cacheData.reservedAt().atZone(ZoneId.systemDefault())
                    .toInstant().toEpochMilli();
            String value = String.valueOf(reservedTimeMillis);

            long ttl = 300; // ttl 5분 설정
            valueOperations.set(key, value, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Error while saving schedule data: {}", e.getMessage());
        }
    }

    public Long getReservedAtTimeMillis(ReservationCommand.GetCache cacheData) {
        if (cacheData.scheduleId() == null || cacheData.userId() == null) {
            log.error("keys must not be null, scheduleId, userId");
            return null;
        }
        try {
            String key = KEY_FORMAT.formatted(cacheData.scheduleId(), cacheData.userId());
            String value = valueOperations.get(key);
            if (value != null) {
                return Long.parseLong(value);
            }
            return null;
        } catch (Exception e) {
            log.error("Error while getting data: {}", e.getMessage());
        }
        return null;
    }

}

