package com.example.movie.service

import com.example.movie.domain.common.TimeHandler
import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.screening.repository.ScreeningRepository
import org.springframework.stereotype.Service

@Service
class MoviesService(
    private val screeningRepository: ScreeningRepository,
    private val timeHandler: TimeHandler
) : NowPlayingMoviesUseCase {

    override fun getNowPlayingMovies(): Map<Movie,List<Screening>> {
        val currentTime = timeHandler.getCurrentTime()
        return screeningRepository.findAllNowPlayingWithMovieAndTheater(currentTime)
    }
}