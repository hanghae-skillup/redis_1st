package com.study.cinema.infra.cache

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import java.time.Duration

@EnableCaching
@Configuration
class CacheConfig(
    private val redisConnectionFactory: RedisConnectionFactory,
) {

    @Bean
    fun cacheManager(): CacheManager {
        val defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(5)) // 기본 TTL
            .prefixCacheNameWith(CACHE_NAME_PREFIX)

        val cacheConfigurations = mapOf(
            "cinemaSchedule-v1" to defaultCacheConfig.entryTtl(Duration.ofMinutes(5)) // cinemaSchedule 캐시
        )

        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(defaultCacheConfig)
            .withInitialCacheConfigurations(cacheConfigurations)
            .build()
    }

    companion object {
        val CACHE_NAME_PREFIX = "cinema-cahe:"
    }
}