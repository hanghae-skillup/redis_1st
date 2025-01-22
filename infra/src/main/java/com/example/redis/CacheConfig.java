package com.example.redis;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .findAndRegisterModules() // JavaTimeModule 자동 등록
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // 날짜를 타임스탬프로 쓰지 않음
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 알 수 없는 속성 무시
    }

    @Bean
    public RedissonClient redissonClient(ObjectMapper objectMapper) {
        Config config = new Config();

        // TypedJsonJacksonCodec 설정
        config.setCodec(new TypedJsonJacksonCodec(Object.class,objectMapper));
        config.useSingleServer()
                .setAddress("redis://localhost:6379"); // Redis 서버 주소 (필요에 따라 변경)

        return Redisson.create(config);
    }

    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) {
        // Cache 설정
        Map<String, org.redisson.spring.cache.CacheConfig> config = new HashMap<>();
        config.put("getMovies", new org.redisson.spring.cache.CacheConfig());
        config.get("getMovies").setTTL(5 * 60 * 1000); // TTL 설정 (5분)

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
