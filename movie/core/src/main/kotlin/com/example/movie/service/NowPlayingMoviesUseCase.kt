package com.example.movie.service

import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.screening.model.ScreeningStatus

interface NowPlayingMoviesUseCase {
    fun getMoviesByStatus(status: ScreeningStatus): Map<Movie,List<Screening>>
}