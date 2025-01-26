package com.study.cinema.infra.jpa.movie

import com.study.cinema.domain.theater.Theater
import org.springframework.data.jpa.repository.JpaRepository

interface TheaterRepository:JpaRepository<Theater, Long> {
}