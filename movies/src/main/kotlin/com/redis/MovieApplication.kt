package com.redis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class MoviesApplication
fun main(args: Array<String>) {
    runApplication<MoviesApplication>(*args)
}