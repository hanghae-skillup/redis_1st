package com.study.cinema.infra.jpa.movie

import com.study.cinema.domain.movie.Movie
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository: JpaRepository<Movie, Long> {
}