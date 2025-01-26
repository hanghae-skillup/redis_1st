package com.hanghae.cinema.adapter;

import com.hanghae.cinema.adapter.persistence.config.JpaConfig;
import com.hanghae.cinema.adapter.persistence.config.PersistenceConfig;
import com.hanghae.cinema.application.config.ApplicationConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
        JpaConfig.class,
        PersistenceConfig.class,
        ApplicationConfig.class
})
public class ApiConfig {
}
