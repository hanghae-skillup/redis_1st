package com.example.movie.service

import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.screening.model.ScreeningStatus

interface MovieSearchUseCase {
    fun getMoviesByStatusAndTitleAndGenre(status: ScreeningStatus, title: String?, genreId:Long?): List<Movie>
}