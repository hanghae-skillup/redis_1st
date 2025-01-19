package com.study.cinema.infra.jpa.movie

import com.study.cinema.domain.cinema.Cinema
import org.springframework.data.jpa.repository.JpaRepository

interface CinemaRepository: JpaRepository<Cinema, Long> {
}