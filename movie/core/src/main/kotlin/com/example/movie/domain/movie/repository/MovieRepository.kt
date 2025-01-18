package com.example.movie.domain.movie.repository

import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.screening.model.ScreeningStatus
import java.time.LocalDateTime

interface MovieRepository {
    fun findById(id: Long): Movie?
    fun findAllOrderByReleaseDateDesc(): List<Movie>
    fun findAllByStatusWithMovieAndTheater(currentTime: LocalDateTime, status: ScreeningStatus, title: String?, genreId: Long?): List<Movie>
}