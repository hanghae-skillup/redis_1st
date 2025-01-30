package com.example.redis.configs

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
@EnableCaching
class CaffeineCacheConfiguration {

    @Bean
    fun cacheManager(): CacheManager {
        val cacheManager = CaffeineCacheManager("titleCache")
        cacheManager.setCaffeine(caffeineCacheBuilder())
        return cacheManager
    }

    fun caffeineCacheBuilder(): Caffeine<Any, Any> {
        return Caffeine.newBuilder()
            .expireAfterWrite(12, TimeUnit.HOURS)
            .maximumSize(100)
    }
}
