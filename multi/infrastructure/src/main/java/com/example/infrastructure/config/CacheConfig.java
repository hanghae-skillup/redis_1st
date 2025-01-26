package com.example.infrastructure.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {
    /*   @Bean
       public Caffeine<Object, Object> caffeineConfig() {
           return Caffeine.newBuilder()
                   .expireAfterWrite(1, TimeUnit.HOURS) // 캐시 TTL 설정 (1시간)
                   .maximumSize(1000);                 // 최대 캐시 저장 개수
       }

       @Bean
       public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
           CaffeineCacheManager cacheManager = new CaffeineCacheManager();
           cacheManager.setCaffeine(caffeine);
           return cacheManager;
       }*/

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379") // 애플리케이션 실행 환경에 맞게 수정
                .setConnectionPoolSize(50)
                .setConnectionMinimumIdleSize(10);
        return Redisson.create(config);
    }



    /*
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // ObjectMapper에 JavaTimeModule 등록
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Redis 직렬화 설정

        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(objectMapper);


        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1)) // TTL 설정
                .serializeValuesWith(

                        RedisSerializationContext.SerializationPair.fromSerializer(serializer)
                );

        return  RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }
*/

}
