package com.movie.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.movie.domain")
@EntityScan(basePackages = "com.movie.domain.entity")
@EnableJpaRepositories(basePackages = "com.movie.domain.repository")
public class DomainApplication {
    public static void main(String[] args) {
        SpringApplication.run(DomainApplication.class, args);
    }
} 