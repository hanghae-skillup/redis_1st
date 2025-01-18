package com.example.movie.service

import com.example.movie.domain.common.TimeHandler
import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.movie.repository.MovieRepository
import com.example.movie.domain.screening.model.ScreeningStatus
import org.springframework.stereotype.Service

@Service
class MovieService(
    private val movieRepository: MovieRepository,
    private val timeHandler: TimeHandler
) : MovieSearchUseCase {

    override fun getMoviesByStatusAndTitleAndGenre(status: ScreeningStatus, title: String?, genreId: Long?): List<Movie> {
        val currentTime = timeHandler.getCurrentTime()
        return movieRepository.findAllByStatusWithMovieAndTheater(currentTime,ScreeningStatus.SCHEDULED, title, genreId)
    }
}