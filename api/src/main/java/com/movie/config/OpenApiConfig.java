package com.movie.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Movie Reservation API")
                .version("v1.0")
                .description("영화 예매 시스템 API 문서");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
} 