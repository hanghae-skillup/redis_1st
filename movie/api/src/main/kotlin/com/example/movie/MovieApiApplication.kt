package com.example.movie

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["com.example.movie"]
)
class MovieApiApplication

fun main(args: Array<String>) {
    runApplication<MovieApiApplication>(*args)
}
