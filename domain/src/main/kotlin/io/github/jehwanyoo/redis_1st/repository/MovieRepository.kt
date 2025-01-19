package io.github.jehwanyoo.redis_1st.repository

import io.github.jehwanyoo.redis_1st.model.Movie

interface MovieRepository {
    fun findAll(): List<Movie>
}
