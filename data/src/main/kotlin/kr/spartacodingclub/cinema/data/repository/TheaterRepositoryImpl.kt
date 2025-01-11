package kr.spartacodingclub.cinema.data.repository

import kr.spartacodingclub.cinema.core.domain.Theater
import kr.spartacodingclub.cinema.core.repository.TheaterRepository
import kr.spartacodingclub.cinema.data.mapper.TheaterMapper
import org.springframework.stereotype.Repository

@Repository
class TheaterRepositoryImpl(
    private val theaterJpaRepository: TheaterJpaRepository
) : TheaterRepository {
    override fun findById(id: Long): Theater? {
        return theaterJpaRepository.findById(id)
            .map(TheaterMapper.INSTANCE::toTheater)
            .orElse(null)
    }
}
