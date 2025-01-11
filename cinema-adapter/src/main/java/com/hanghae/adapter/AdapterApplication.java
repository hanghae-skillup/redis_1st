package com.hanghae.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// 다른 모듈의 빈을 찾지 못해 scanBasePackages 설정
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.hanghae.infrastructure.repository")
@EntityScan(basePackages = "com.hanghae.infrastructure.entity")
@ComponentScan(basePackages = {
        "com.hanghae.domain",
        "com.hanghae.application",
        "com.hanghae.infrastructure",
        "com.hanghae.adapter"
})
public class AdapterApplication {
    public static void main(String[] args) {
        //서버 실행시 어뎁터로 실행
        SpringApplication.run(AdapterApplication.class, args);
    }
}
