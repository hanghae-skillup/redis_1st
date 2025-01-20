package io.github.jehwanyoo.redis_1st.cache

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
@EnableCaching
class CacheConfig {

    @Bean
    fun cacheManager(): CaffeineCacheManager {
        val caffeine = Caffeine.newBuilder()
            .expireAfterWrite(60, TimeUnit.MINUTES)     // 캐시 생성(작성) 후 60분이 지나면 만료
            .initialCapacity(100)                       // 초기 용량
            .maximumSize(1000)                          // 최대 캐시 사이즈

        val cacheManager = CaffeineCacheManager()
        cacheManager.setCaffeine(caffeine)
        return cacheManager
    }
}
