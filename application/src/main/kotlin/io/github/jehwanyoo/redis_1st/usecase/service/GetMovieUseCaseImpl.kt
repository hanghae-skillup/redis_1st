package io.github.jehwanyoo.redis_1st.usecase.service

import io.github.jehwanyoo.redis_1st.model.Movie
import io.github.jehwanyoo.redis_1st.repository.port.MovieRepository
import io.github.jehwanyoo.redis_1st.usecase.port.GetMoviesUseCase
import org.springframework.stereotype.Service

@Service
class GetMovieUseCaseImpl(
    private val movieRepository: MovieRepository,
) : GetMoviesUseCase {
    override fun execute(): List<Movie> {
        return movieRepository.findAll()
    }
}
