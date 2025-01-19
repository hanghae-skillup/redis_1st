package com.movie.domain.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CaffeineCacheConfig {

    @Bean
    public CacheManager cacheManager() {
        List<CaffeineCache> caches = Arrays.stream(CacheType.values())
                .map(cache -> new CaffeineCache(cache.getCacheName(),
                        Caffeine.newBuilder().recordStats()
                                .expireAfterWrite(cache.getExpiredAfterWrite(), TimeUnit.MINUTES)
//                                .refreshAfterWrite(30, TimeUnit.MINUTES)      // 30분 간격으로 refresh
                                .maximumSize(cache.getMaximumSize())
                                .build())).toList();
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caches);

        return cacheManager;
    }

}
