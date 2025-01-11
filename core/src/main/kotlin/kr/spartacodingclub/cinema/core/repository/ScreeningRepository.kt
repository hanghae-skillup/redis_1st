package kr.spartacodingclub.cinema.core.repository

import kr.spartacodingclub.cinema.core.domain.Screening

interface ScreeningRepository {
    fun findAllByMovieIdOrderByStartTimeAsc(movieId: Long): List<Screening>
}
