package com.example.redis.movie.out.persistence.jpa

import com.example.redis.movie.Movie
import com.example.redis.movie.out.persistence.jpa.QMovieEntity.*
import com.example.redis.movie.out.persistence.jpa.QMovieGenreEntity.*
//import com.example.redis.movie.out.persistence.jpa.QMovieGenreEntity.*
import com.example.redis.movie.out.persistence.jpa.QScreeningEntity.*
import com.example.redis.theater.out.persistence.jpa.QTheaterEntity
import com.example.redis.theater.out.persistence.jpa.QTheaterEntity.*
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.core.types.dsl.StringTemplate
import com.querydsl.core.util.StringUtils
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.Lock
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import kotlin.streams.toList

@Repository
class MovieRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
): MovieRepositoryCustom {

    override fun findByOrderByReleaseDateDesc(title: String?, genre: String?): MutableList<MovieEntity> {

        val movieIds = mutableListOf<Long>()
        if (!genre.isNullOrEmpty()) {
            movieIds.addAll(
                queryFactory.select(movieGenreEntity.id)
                    .from(movieGenreEntity)
                    .where(movieGenreEntity.name.eq(genre))
                    .fetch()
            )
        }

        return queryFactory.select(movieEntity)
            .from(movieEntity)
            .join(movieEntity.screening, screeningEntity)
            .fetchJoin()
            .join(screeningEntity.theater, theaterEntity)
            .fetchJoin()
            .where(likeTitle(title), eqMovieGenre(movieIds))
            .orderBy(movieEntity.releaseDate.desc())
            .fetch()
    }

    fun likeTitle(title: String?): BooleanExpression? {
        if (StringUtils.isNullOrEmpty(title)) {
            return null
        }
        val matchExpression = Expressions.numberTemplate(BigDecimal::class.java,
            "function('MATCH', {0}, {1})",
            movieEntity.title,
            title).gt(0);
        return matchExpression
    }

    fun eqMovieGenre(movieIds: MutableList<Long>): BooleanExpression? {
        return if(movieIds.isEmpty()) null else movieEntity.id.`in`(movieIds)
    }

}