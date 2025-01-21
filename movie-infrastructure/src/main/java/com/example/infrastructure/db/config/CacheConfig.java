package com.example.infrastructure.db.config;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@RequiredArgsConstructor
@EnableCaching
@Configuration
public class CacheConfig {

    /**
     * Redis 기본 캐시 설정을 정의하는 메서드
     * - 모든 Redis 캐시에 적용될 기본 설정을 정의
     * - TTL(캐시 유효 시간): 60초로 설정
     * - NULL 값 캐싱 방지
     * - Key는 문자열(String), Value는 JSON으로 직렬화
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(20)) // 캐시 만료 시간
                .disableCachingNullValues() // null 값 캐싱 방지
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));
    }
}
