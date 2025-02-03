package org.example.config;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, org.redisson.spring.cache.CacheConfig> configMap = new HashMap<>();
        configMap.put("playingMovies", new org.redisson.spring.cache.CacheConfig(0, 0)); // 영구 저장
        return new RedissonSpringCacheManager(redissonClient, configMap);
    }
}
