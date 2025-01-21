package com.example.config;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedissonCacheConfig {
    @Bean
    public CacheManager movieCacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> config = new HashMap<>();

        config.put("movies", new CacheConfig(60 * 1000, 0));
        return new RedissonSpringCacheManager(redissonClient, config);
    }
}
