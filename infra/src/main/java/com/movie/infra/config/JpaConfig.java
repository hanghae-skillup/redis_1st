package com.movie.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.movie.infra.repository", "com.movie.domain.repository"})
public class JpaConfig {

}