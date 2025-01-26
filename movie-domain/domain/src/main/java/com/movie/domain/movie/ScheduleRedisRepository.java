package com.movie.domain.movie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.domain.movie.dto.command.ScheduleCommand;
import com.movie.domain.movie.dto.info.ScheduleInfo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ScheduleRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    private ValueOperations<String, String> valueOperations;

    @PostConstruct
    public void init() {
        this.valueOperations = redisTemplate.opsForValue();
    }

    public void save(ScheduleCommand.Search search, List<ScheduleInfo.Get> schedules) {
        if (search.title() == null || search.genre() == null) {
            log.error("keys must not be null");
            return;
        }
        try {
            String key = "%s:%s".formatted(search.title(), search.genre());
            String value = serializeScheduleValues(schedules);

            long ttl = 600; // ttl 10분 설정
            valueOperations.set(key, value, ttl, TimeUnit.SECONDS);
            log.info("schedule cache saved: key={}", key);
        } catch (Exception e) {
            log.error("Error while saving schedule data: {}", e.getMessage());
        }
    }

    public List<ScheduleInfo.Get> find(ScheduleCommand.Search search) {
        if (search.title() == null || search.genre() == null) {
            log.error("keys must not be null");
            return Collections.emptyList();
        }
        try {
            String key = "%s:%s".formatted(search.title(), search.genre());
            String value = valueOperations.get(key);

            if (StringUtils.hasText(value)) {
                return deserializeScheduleValues(value);
            }
        } catch (Exception e) {
            log.error("Error while getting schedule data: {}", e.getMessage());
        }
        return Collections.emptyList();
    }

    private String serializeScheduleValues(List<ScheduleInfo.Get> schedules) throws JsonProcessingException {
        return objectMapper.writeValueAsString(schedules);
    }

    private List<ScheduleInfo.Get> deserializeScheduleValues(String value) throws JsonProcessingException {
        return objectMapper.readValue(value, new TypeReference<>() {});
    }

}
