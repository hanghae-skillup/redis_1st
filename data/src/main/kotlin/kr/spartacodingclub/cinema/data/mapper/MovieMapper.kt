package kr.spartacodingclub.cinema.data.mapper

import kr.spartacodingclub.cinema.core.domain.Movie
import kr.spartacodingclub.cinema.data.entity.MovieEntity
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface MovieMapper {
    companion object {
        val INSTANCE: MovieMapper = Mappers.getMapper(MovieMapper::class.java)
    }

    fun toMovie(entity: MovieEntity): Movie
    fun toMovieEntity(domain: Movie): MovieEntity
}
