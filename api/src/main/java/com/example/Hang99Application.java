package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@SpringBootApplication
public class Hang99Application {

    public static void main(String[] args) {
        SpringApplication.run(Hang99Application.class, args);
    }

    @Bean
    public AuditorAware<String> auditorProvider(){
        return () -> Optional.of("SYSTEM");
    }
}