package com.example.infrastructure.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379")
                .setConnectionPoolSize(10)
                .setConnectionMinimumIdleSize(5)
                .setTimeout(5000)          // 응답 타임아웃 (명령 실행에 대한 응답)
                .setRetryAttempts(3)       // 최대 3번 재시도
                .setRetryInterval(1500)    // 재시도 간격 1.5초
                .setConnectTimeout(5000);  // 연결 타임아웃 (연결 시도에 대한 응답)

        return Redisson.create(config);
    }

}
