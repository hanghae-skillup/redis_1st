package com.example.redis.movie.`in`.movie

import com.example.redis.movie.Movie
import com.example.redis.movie.Reservation
import com.example.redis.movie.out.movie.MoviePort
import com.example.redis.movie.out.movie.TheaterPort
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MovieService(
    private val movieAdapter: MoviePort,
    private val theaterAdapter: TheaterPort,
): MovieUseCase {

    @Cacheable(value = ["movieSearchCache"], keyGenerator = "movieSearchKeyGenerator")
    @Transactional(readOnly = true)
    override fun gets(title: String?, genre: String?): MutableList<Movie> {
        val movies = movieAdapter.findByOrderByReleaseDateDesc(title, genre)
        return movies
    }

    @Transactional(readOnly = false)
    override fun reserve(movieId: Long, reservation: Reservation): String {
        val id = movieAdapter.reserve(reservation)
        return id
    }
}