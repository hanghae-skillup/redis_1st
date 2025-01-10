package com.example.movie.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@TestConfiguration
@EnableJpaRepositories(basePackages = ["com.example.movie.persistence"])
@EntityScan(basePackages = ["com.example.movie.persistence"])
class TestJpaConfig {
}