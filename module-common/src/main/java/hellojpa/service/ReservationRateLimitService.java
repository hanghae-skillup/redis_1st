package hellojpa.service;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationRateLimitService {

    private final RedissonClient redissonClient;

    private final String luaScript =
            "local key = KEYS[1] " +
                    "local ttl = tonumber(ARGV[1]) " +
                    "if redis.call('EXISTS', key) == 1 then return 0 end " +
                    "redis.call('SET', key, 1, 'EX', ttl) " +
                    "return 1";

    public boolean isAllowed(Long userId, Long screeningId) {
        String key = String.format("rate_limit:%d:%d", userId, screeningId);
        RScript script = redissonClient.getScript();

        List<Object> keys = Collections.singletonList(key);
        List<Object> args = Collections.singletonList(300); // TTL 5분 (300초)

        // Lua 스크립트 실행
        Long result = script.eval(RScript.Mode.READ_WRITE, luaScript, RScript.ReturnType.INTEGER, keys, args.toArray());

        System.out.println("Lua Script Result: " + result); // 디버깅 메시지

        return result != 0;
    }
}