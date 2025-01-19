package io.github.jehwanyoo.redis_1st.repository

import io.github.jehwanyoo.redis_1st.entity.MovieEntity
import io.github.jehwanyoo.redis_1st.model.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MovieRepositoryImpl(
    private val jpaMovieRepository: JpaMovieRepository
) : MovieRepository {
    override fun findAll(): List<Movie> {
        val entities = jpaMovieRepository.findAll()
        return entities.map { entity -> entity.toDomain() }
    }
}

interface JpaMovieRepository : JpaRepository<MovieEntity, UUID>
