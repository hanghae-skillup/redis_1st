package kr.spartacodingclub.cinema.config

import kr.spartacodingclub.cinema.core.repository.MovieRepository
import kr.spartacodingclub.cinema.core.repository.ScreeningRepository
import kr.spartacodingclub.cinema.core.usecase.GetMovieSchedulesUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfig(
    private val movieRepository: MovieRepository,
    private val screeningRepository: ScreeningRepository
) {
    @Bean
    fun getMovieSchedulesUseCase(): GetMovieSchedulesUseCase {
        return GetMovieSchedulesUseCase(
            movieRepository = movieRepository,
            screeningRepository = screeningRepository
        )
    }
}
