package kr.spartacodingclub.cinema.data.repository

import kr.spartacodingclub.cinema.core.domain.Movie
import kr.spartacodingclub.cinema.core.repository.MovieRepository
import kr.spartacodingclub.cinema.data.mapper.MovieMapper
import org.springframework.stereotype.Repository

@Repository
class MovieRepositoryImpl(
    private val movieJpaRepository: MovieJpaRepository
) : MovieRepository {
    override fun findAllOrderByReleaseDateDesc(): List<Movie> {
        return movieJpaRepository.findAllByOrderByReleaseDateDesc()
            .map(MovieMapper.INSTANCE::toMovie)
    }
    
    override fun findById(id: Long): Movie? {
        return movieJpaRepository.findById(id)
            .map(MovieMapper.INSTANCE::toMovie)
            .orElse(null)
    }
} 