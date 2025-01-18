package com.example.redis.movie.out.persistence.jpa

import com.example.redis.movie.out.persistence.jpa.QMovieEntity.*
//import com.example.redis.movie.out.persistence.jpa.QMovieGenreEntity.*
import com.example.redis.movie.out.persistence.jpa.QMovieTheaterEntity.*
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.util.StringUtils
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
@Repository
class MovieRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
): MovieRepositoryCustom {

    override fun findByOrderByReleaseDateDesc(title: String?, genre: String?): MutableList<MovieEntity> {
        return queryFactory.select(movieEntity)
            .from(movieEntity)
//            .join(movieEntity.movieGenre, movieGenreEntity)
//            .fetchJoin()
            .join(movieEntity.movieTheaters, movieTheaterEntity)
            .fetchJoin()
            .where(eqTitle(title), eqMovieGenre(genre))
            .orderBy(movieEntity.releaseDate.desc())
            .fetch()

    }

    fun eqTitle(title: String?): BooleanExpression? {
        return if (StringUtils.isNullOrEmpty(title)) null else movieEntity.title.eq(title)
    }

    fun eqMovieGenre(genre: String?): BooleanExpression? {
        val movieGenre = movieEntity.movieGenre.any() // @ElementCollection 필드 접근
        return if (StringUtils.isNullOrEmpty(genre)) null else movieGenre.eq(genre)
    }
}