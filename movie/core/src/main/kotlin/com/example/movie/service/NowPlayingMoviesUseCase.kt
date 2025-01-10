package com.example.movie.service

import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.screening.model.Screening

interface NowPlayingMoviesUseCase {
    fun getNowPlayingMovies(): Map<Movie,List<Screening>>
}