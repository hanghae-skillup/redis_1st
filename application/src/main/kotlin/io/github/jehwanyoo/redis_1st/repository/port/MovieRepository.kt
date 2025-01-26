package io.github.jehwanyoo.redis_1st.repository.port

import io.github.jehwanyoo.redis_1st.model.Movie

interface MovieRepository {
    fun findAll(): List<Movie>
    fun findByTitleContaining(title: String): List<Movie>
}
