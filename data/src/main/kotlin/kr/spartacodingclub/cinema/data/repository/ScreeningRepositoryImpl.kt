package kr.spartacodingclub.cinema.data.repository

import kr.spartacodingclub.cinema.core.domain.Screening
import kr.spartacodingclub.cinema.core.repository.ScreeningRepository
import org.springframework.stereotype.Repository

@Repository
class ScreeningRepositoryImpl(
    private val screeningJpaRepository: ScreeningJpaRepository
) : ScreeningRepository {
    override fun findAllByMovieIdOrderByStartTimeAsc(movieId: Long): List<Screening> {
        return screeningJpaRepository.findAllByMovieIdWithMovieAndTheaterOrderByStartTimeAsc(movieId)
            .map { it.toDomain() }
    }
}
