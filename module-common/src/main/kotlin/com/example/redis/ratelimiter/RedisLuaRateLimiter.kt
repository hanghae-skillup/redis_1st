package com.example.redis.ratelimiter

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.script.DefaultRedisScript
import org.springframework.stereotype.Component

@Component("redisLuaRateLimiter")
class RedisLuaRateLimiter(
    private val redisTemplate: RedisTemplate<String, String>
): RateLimiter {
    private val luaScript = """
            local key = KEYS[1]
            local limit = tonumber(ARGV[1])
            local ttl = tonumber(ARGV[2])
            
            local current_count = redis.call("GET", key)
            
            if current_count and tonumber(current_count) > limit then
                return 0 -- Rate limit 초과
            end
            
            current_count = redis.call("INCR", key)
            
            if current_count == 1 then
                redis.call("EXPIRE", key, ttl)
            end

            return 1
        """.trimIndent()

    override fun tryAcquire(api: String, ip: String, rate: Long): Boolean {

        val key = "$api:$ip"
        val script = DefaultRedisScript(luaScript, Long::class.java)

        val result = redisTemplate.execute(
            script,
            listOf(key),
            rate.toString(),
            "60"
        )
        return result == 1L
    }
}