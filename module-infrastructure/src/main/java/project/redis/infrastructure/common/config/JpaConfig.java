package project.redis.infrastructure.common.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {
        "project.redis.infrastructure"
})
@EntityScan(basePackages = {
        "project.redis.infrastructure"
})
public class JpaConfig {
}
