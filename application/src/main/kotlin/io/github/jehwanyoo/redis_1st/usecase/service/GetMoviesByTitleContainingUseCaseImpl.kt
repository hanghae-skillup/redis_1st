package io.github.jehwanyoo.redis_1st.usecase.service

import io.github.jehwanyoo.redis_1st.model.Movie
import io.github.jehwanyoo.redis_1st.repository.port.MovieRepository
import io.github.jehwanyoo.redis_1st.usecase.port.GetMoviesByTitleContainingUseCase
import org.springframework.stereotype.Service

@Service
class GetMoviesByTitleContainingUseCaseImpl(
    private val movieRepository: MovieRepository,
) : GetMoviesByTitleContainingUseCase {
    override fun execute(title: String): List<Movie> {
        return movieRepository.findByTitleContaining(title)
    }
}
