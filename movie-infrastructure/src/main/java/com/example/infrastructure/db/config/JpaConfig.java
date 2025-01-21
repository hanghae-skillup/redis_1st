package com.example.infrastructure.db.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // 현재 사용자는 임의로 고정, 실제 사용자 정보를 불러오는 로직 작성 가능
        return () -> Optional.of("System");
    }

}
