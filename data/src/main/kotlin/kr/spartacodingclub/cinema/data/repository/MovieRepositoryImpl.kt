package kr.spartacodingclub.cinema.data.repository

import kr.spartacodingclub.cinema.core.domain.Movie
import kr.spartacodingclub.cinema.core.repository.MovieRepository
import kr.spartacodingclub.cinema.core.repository.MovieScreening
import kr.spartacodingclub.cinema.core.repository.MovieWithScreenings
import kr.spartacodingclub.cinema.data.mapper.MovieMapper
import org.springframework.stereotype.Repository

@Repository
class MovieRepositoryImpl(
    private val movieJpaRepository: MovieJpaRepository
) : MovieRepository {
    override fun findAllOrderByReleaseDateDesc(): List<Movie> {
        return movieJpaRepository.findAllWithScreeningsAndTheaterOrderByReleaseDateDesc()
            .map(MovieMapper.INSTANCE::toMovie)
    }
    
    override fun findById(id: Long): Movie? {
        return movieJpaRepository.findByIdWithScreeningsAndTheater(id)
            .map(MovieMapper.INSTANCE::toMovie)
            .orElse(null)
    }

    override fun findAllWithScreeningsOrderByReleaseDateDesc(): List<MovieWithScreenings> {
        return movieJpaRepository.findAllWithScreeningsAndTheaterOrderByReleaseDateDesc()
            .map { movieEntity ->
                MovieWithScreenings(
                    movie = MovieMapper.INSTANCE.toMovie(movieEntity),
                    screenings = movieEntity.screenings.map { screening ->
                        MovieScreening(
                            theater = screening.theater.toDomain(),
                            startTime = screening.startTime
                        )
                    }
                )
            }
    }
} 