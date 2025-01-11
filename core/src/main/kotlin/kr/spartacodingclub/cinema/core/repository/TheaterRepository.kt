package kr.spartacodingclub.cinema.core.repository

import kr.spartacodingclub.cinema.core.domain.Theater

interface TheaterRepository {
    fun findById(id: Long): Theater?
}
