package com.movie.moviestorage.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "com.movie.moviestorage")
@EnableJpaRepositories(basePackages = "com.movie.moviestorage")
public class StorageConfig {
}
