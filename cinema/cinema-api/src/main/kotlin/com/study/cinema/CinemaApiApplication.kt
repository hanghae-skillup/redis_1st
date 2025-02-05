package com.study.cinema

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CinemaApiApplication

fun main(args: Array<String>) {
    runApplication<CinemaApiApplication>(*args)
}
