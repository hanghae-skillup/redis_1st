//package com.example.domain.config;
//
//import com.github.benmanes.caffeine.cache.Caffeine;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.caffeine.CaffeineCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//@EnableCaching
//public class LocalCacheConfig {
//    @Bean
//    public Caffeine<Object, Object> caffeineConfig() {
//        return Caffeine.newBuilder()
//                .expireAfterWrite(10, TimeUnit.MINUTES) // 캐시 TTL 설정
//                .maximumSize(1000); // 최대 캐시 항목 개수
//    }
//
//
//    @Bean
//    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
//        return new CaffeineCacheManager("screeningCache");
//    }
//}
