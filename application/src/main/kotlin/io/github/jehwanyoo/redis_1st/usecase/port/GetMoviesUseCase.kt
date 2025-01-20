package io.github.jehwanyoo.redis_1st.usecase.port

import io.github.jehwanyoo.redis_1st.model.Movie

interface GetMoviesUseCase {
    fun execute(): List<Movie>
}
