package io.github.jehwanyoo.redis_1st.cache

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import java.time.Duration

@Configuration
@EnableCaching
class CacheConfig {
// 로컬 인메모리 캐시
//    @Bean
//    fun cacheManager(): CaffeineCacheManager {
//        val caffeine = Caffeine.newBuilder()
//            .expireAfterWrite(60, TimeUnit.MINUTES)     // 캐시 생성(작성) 후 60분이 지나면 만료
//            .initialCapacity(100)                       // 초기 용량
//            .maximumSize(1000)                          // 최대 캐시 사이즈
//
//        val cacheManager = CaffeineCacheManager()
//        cacheManager.setCaffeine(caffeine)
//        return cacheManager
//    }

    // 레디스 캐시
    @Bean
    fun cacheManager(
        redisConnectionFactory: RedisConnectionFactory,
    ): RedisCacheManager {
        val objectMapper = ObjectMapper()
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

        val serializer = Jackson2JsonRedisSerializer(objectMapper, Any::class.java)

        val defaultCacheConfig = RedisCacheConfiguration
            .defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(60))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(serializer)
            )

        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(defaultCacheConfig)
            .build()
    }
}
