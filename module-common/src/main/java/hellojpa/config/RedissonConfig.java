package hellojpa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379");

        // Jackson ObjectMapper 설정
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Java 8 Time API 지원
        config.setCodec(new JsonJacksonCodec(objectMapper));

        return Redisson.create(config);
    }

    @Bean
    public RedissonSpringCacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> configMap = new HashMap<>();

        //  테스트하기 위해 TTL 10분으로 설정
        configMap.put("screenings", new org.redisson.spring.cache.CacheConfig(600000, 120000)); // 테스트 끝난 뒤 TTL 86400000(24시간)으로 변경

        return new RedissonSpringCacheManager(redissonClient, configMap);
    }
}
