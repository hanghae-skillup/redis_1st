package io.github.jehwanyoo.redis_1st.use_case

import io.github.jehwanyoo.redis_1st.model.Movie
import io.github.jehwanyoo.redis_1st.repository.MovieRepository
import org.springframework.stereotype.Service

@Service
class GetMoviesUseCase(
    private val movieRepository: MovieRepository,
) {
    fun execute(): List<Movie> {
        return movieRepository.findAll()
    }
}
