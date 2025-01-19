package com.example.redis.configs;

@org.springframework.context.annotation.Configuration()
@org.springframework.cache.annotation.EnableCaching()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0017J\u0014\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u0006H\u0016\u00a8\u0006\u0007"}, d2 = {"Lcom/example/redis/configs/CaffeineCacheConfiguration;", "", "()V", "cacheManager", "Lorg/springframework/cache/CacheManager;", "caffeineCacheBuilder", "Lcom/github/benmanes/caffeine/cache/Caffeine;", "module-common"})
public class CaffeineCacheConfiguration {
    
    public CaffeineCacheConfiguration() {
        super();
    }
    
    @org.springframework.context.annotation.Bean()
    @org.jetbrains.annotations.NotNull()
    public org.springframework.cache.CacheManager cacheManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.github.benmanes.caffeine.cache.Caffeine<java.lang.Object, java.lang.Object> caffeineCacheBuilder() {
        return null;
    }
}