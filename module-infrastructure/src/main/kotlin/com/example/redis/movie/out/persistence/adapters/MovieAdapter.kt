package com.example.redis.movie.out.persistence.adapters

import com.example.redis.movie.Movie
import com.example.redis.movie.out.mapper.MoviePersistenceMapper
import com.example.redis.movie.out.movie.MoviePort
import com.example.redis.movie.out.persistence.jpa.MovieRepository
import org.springframework.stereotype.Service

@Service
class MovieAdapter(
    private val movieRepository: MovieRepository
): MoviePort {
    override fun findByOrderByReleaseDateDesc(title: String?, genre: String?): MutableList<Movie> {
        val movies = this.movieRepository.findByOrderByReleaseDateDesc(title, genre)
        return movies
            .stream()
            .map { MoviePersistenceMapper.toMovieDomain(it) }.toList()
    }
}