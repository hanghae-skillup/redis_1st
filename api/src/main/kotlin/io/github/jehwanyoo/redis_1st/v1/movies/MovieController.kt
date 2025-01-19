package io.github.jehwanyoo.redis_1st.v1.movies

import io.github.jehwanyoo.redis_1st.use_case.GetMoviesUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class MovieController(
    private val getMovieDetailsUseCase: GetMoviesUseCase
) {

    @GetMapping("/movies")
    fun getMovies(): List<MovieResponse> {
        val movieDetail = getMovieDetailsUseCase.execute()
        return MovieResponse.fromDomain(movieDetail)
    }
}
