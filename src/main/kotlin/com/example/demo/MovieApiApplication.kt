package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieApiApplication

fun main(args: Array<String>) {
    runApplication<MovieApiApplication>(*args)
} 