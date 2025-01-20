package io.github.jehwanyoo.redis_1st.v1.movies

import io.github.jehwanyoo.redis_1st.usecase.port.GetMoviesByTitleContainingUseCase
import io.github.jehwanyoo.redis_1st.usecase.port.GetMoviesUseCase
import io.github.jehwanyoo.redis_1st.v1.BaseApiV1Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieController(
    private val getMovieDetailsUseCase: GetMoviesUseCase,
    private val getMoviesByTitleContainingUseCase: GetMoviesByTitleContainingUseCase,
) : BaseApiV1Controller() {

    @GetMapping("/movies")
    fun getMovies(): List<MovieResponse> {
        val movieDetail = getMovieDetailsUseCase.execute()
        return MovieResponse.fromDomain(movieDetail)
    }

    @GetMapping("/movies/search")
    fun getMoviesSearch(@RequestParam(required = false) title: String?): List<MovieResponse> {
        if (title != null) {
            val movies = getMoviesByTitleContainingUseCase.execute(title)
            return MovieResponse.fromDomain(movies)
        } else {
            return getMovies()
        }
    }
}
