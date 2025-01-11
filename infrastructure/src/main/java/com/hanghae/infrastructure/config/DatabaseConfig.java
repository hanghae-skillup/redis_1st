package com.hanghae.infrastructure.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.hanghae.domain.repository")
public class DatabaseConfig {
    // 데이터베이스 관련 설정
}