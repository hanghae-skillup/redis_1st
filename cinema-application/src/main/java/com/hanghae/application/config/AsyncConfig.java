package com.hanghae.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean(name = "messageTaskExecutor")
    public Executor messageTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);  // 기본 실행 스레드 개수
        executor.setMaxPoolSize(10);  // 최대 스레드 개수
        executor.setQueueCapacity(50);  // 대기 큐 크기
        executor.setThreadNamePrefix("message-async-"); // 스레드의 이름
        executor.initialize();
        return executor;
    }
}