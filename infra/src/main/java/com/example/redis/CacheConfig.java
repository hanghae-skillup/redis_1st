package com.example.redis;

import com.example.jpa.repository.movie.dto.MoviesDetailDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@EnableCaching
@Configuration
public class CacheConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());  // LocalDateTime 등 Java 8 시간 타입을 처리
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);  // 타임스탬프 대신 ISO 8601 포맷
        return objectMapper;
    }

    @Bean
    public RedissonClient redissonClient(ObjectMapper objectMapper) {
        Config config = new Config();

        // TypedJsonJacksonCodec 설정
        config.setCodec(new TypedJsonJacksonCodec(MoviesDetailDto.class,objectMapper));
        config.useSingleServer()
                .setAddress(String.format("redis://%s:%d", host, port));

        return Redisson.create(config);
    }

    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) {
        // Cache 설정
        Map<String, org.redisson.spring.cache.CacheConfig> config = new HashMap<>();
        config.put("getMoviesByGenre", new org.redisson.spring.cache.CacheConfig());
        config.get("getMoviesByGenre").setTTL(5 * 60 * 1000); // TTL 설정 (5분)

        // RedissonSpringCacheManager 사용
        return new RedissonSpringCacheManager(redissonClient, config);
    }

//    @Bean
//    public Caffeine caffeineConfig() {
//        return Caffeine.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES);
//    }
//
//    @Bean
//    public CacheManager cacheManager(Caffeine caffeine) {
//        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
//        caffeineCacheManager.setCaffeine(caffeine);
//
//        return caffeineCacheManager;
//    }

}
