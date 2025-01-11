package io.github.jehwanyoo.redis_1st.use_case

import io.github.jehwanyoo.redis_1st.aggregate.MovieDetail
import io.github.jehwanyoo.redis_1st.repository.MovieRepository
import org.springframework.stereotype.Service

@Service
class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository,
) {

    fun execute(): List<MovieDetail> {
        return movieRepository.findAll().map { movie ->
            MovieDetail(
                movie = movie,
                screens = emptyList(),
                showTimes = emptyList(),
            )
        }
    }
}
