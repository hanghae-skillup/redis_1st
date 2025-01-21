package com.example.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.example.domain.model.entity") // 엔티티 스캔
@EnableJpaRepositories(basePackages = "com.example.infrastructure.db") // 레포지토리 스캔
@ComponentScan(basePackages = {"com.example"}) // 모든 모듈의 빈 스캔
@SpringBootApplication
public class infrastructureApplication {

	public static void main(String[] args) {
		SpringApplication.run(infrastructureApplication.class, args);
	}

}
