package com.example.movie.domain.screening.repository

import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.screening.model.Screening
import java.time.LocalDateTime

interface ScreeningRepository {
    fun findById(id: Long): Screening?
    fun findAllNowPlayingWithMovieAndTheater(currentTime: LocalDateTime): Map<Movie, List<Screening>>
}