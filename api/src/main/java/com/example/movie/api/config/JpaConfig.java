package com.example.movie.api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.example.movie"})
@EnableJpaRepositories(basePackages = {"com.example.movie"})
@EnableJpaAuditing
@Configuration
public class JpaConfig {

}
