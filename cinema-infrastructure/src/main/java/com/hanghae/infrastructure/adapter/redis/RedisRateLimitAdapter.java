package com.hanghae.infrastructure.adapter.redis;

import com.hanghae.application.port.out.redis.RedisRateLimitPort;
import com.hanghae.infrastructure.lua.LuaScriptLoader;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RScript;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class RedisRateLimitAdapter implements RedisRateLimitPort {
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 비정상적 사용 패턴을 감지하고 시스템을 보호하기 위해
     * 1분 내 50회 이상 요청 시 1시간 동안 해당 IP 를 차단
     */
    public boolean isAllowed(String ip) {
        String luaScriptPath = "lua/searchRateLimit.lua";
        int maxRequest = 50; // 최대 요청 횟수
        int expireTime = 60; // 요청 카운트 만료 시간 (초)
        int blockTime = 3600; // 차단 시간 (초)

        String requestKey = "rate_limit:" + ip; // 요청 카운트 키
        String blockKey = "blocked_ip:" + ip; // 차단된 IP 키

        //lua script 가져오기
        String luaScript = LuaScriptLoader.loadScript(luaScriptPath);

        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptText(luaScript);
        script.setResultType(Long.class);

        Long result = redisTemplate.execute(
                script,
                Arrays.asList(requestKey, blockKey), // KEYS[1], KEYS[2]
                String.valueOf(maxRequest), // ARGV[1]
                String.valueOf(expireTime), // ARGV[2]
                String.valueOf(blockTime) // ARGV[3]
        );

        return result != null && result == 1;
    }

    /**
     * 같은 시간대의 영화는 유저당 5분에 1번씩만 예매 가능
     * (체크와 동시에 키생성)
     */
    @Override
    public boolean tryReserveWithLimit(Long scheduleId, Long memberId) {
        String luaScriptPath = "lua/reservationRateLimit.lua";
        int reservationCooldownSecound = 300; // 예약 제한 시간 (초)
        String key = "reservation_limit:" + scheduleId + ":" + memberId; // 레디스 키

        //lua script 가져오기
        String luaScript = LuaScriptLoader.loadScript(luaScriptPath);

        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptText(luaScript);
        script.setResultType(Long.class);

        Long result = redisTemplate.execute(
                script,
                Collections.singletonList(key), // KEYS[1]
                String.valueOf(reservationCooldownSecound) // ARGV[1]
        );

        return result != null && result == 1;
        
        /*
        //lua script 없이 아래 방법으로도 가능

        Boolean isSet = redisTemplate.opsForValue().setIfAbsent(
                key, "1", Duration.ofSeconds(reservationCooldownSecound));
         //해당 키가 이미 존재하면 아무것도 하지 않고 false를 반환

        return Boolean.TRUE.equals(isSet); // 값이 설정되었다면 예약 가능
        */
    }

    /**
     * 5분 제한 여부 확인 (제한된 경우 false 반환)
     */
    @Override
    public boolean canReserve(Long scheduleId, Long memberId) {
        String key = getReservationKey(scheduleId, memberId);
        return Boolean.FALSE.equals(redisTemplate.hasKey(key)); // 키가 없으면 예약 가능
    }

    /**
     * 예매 성공 후 5분 제한 설정
     */
    @Override
    public void setReservationLimit(Long scheduleId, Long memberId) {
        String key = getReservationKey(scheduleId, memberId);
        int reservationCooldownSecound = 300; // 예약 제한 시간 (초)
        redisTemplate.opsForValue().set(key, "1", Duration.ofSeconds(reservationCooldownSecound));
    }

    private String getReservationKey(Long scheduleId, Long memberId) {
        return "reservation_limit:" + scheduleId + ":" + memberId;
    }
}
