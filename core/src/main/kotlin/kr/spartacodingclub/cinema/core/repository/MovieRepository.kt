package kr.spartacodingclub.cinema.core.repository

import kr.spartacodingclub.cinema.core.domain.Movie

interface MovieRepository {
    fun findAllOrderByReleaseDateDesc(): List<Movie>
    fun findById(id: Long): Movie?
}
