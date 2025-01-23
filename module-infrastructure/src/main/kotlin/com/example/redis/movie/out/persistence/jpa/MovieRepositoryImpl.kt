package com.example.redis.movie.out.persistence.jpa

import com.example.redis.movie.Movie
import com.example.redis.movie.out.persistence.jpa.QMovieEntity.*
import com.example.redis.movie.out.persistence.jpa.QMovieGenreEntity.*
//import com.example.redis.movie.out.persistence.jpa.QMovieGenreEntity.*
import com.example.redis.movie.out.persistence.jpa.QMovieTheaterEntity.*
import com.example.redis.theater.out.persistence.jpa.QTheaterEntity
import com.example.redis.theater.out.persistence.jpa.QTheaterEntity.*
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.core.types.dsl.StringTemplate
import com.querydsl.core.util.StringUtils
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
class MovieRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
): MovieRepositoryCustom {

    override fun findByOrderByReleaseDateDesc(title: String?, genre: String?): MutableList<MovieEntity> {

        return queryFactory.select(movieEntity)
            .from(movieEntity)
            .leftJoin(movieEntity.movieTheaters, movieTheaterEntity)
            .fetchJoin()
            .leftJoin(movieTheaterEntity.theater, theaterEntity)
            .fetchJoin()
            .where(likeTitle(title), eqMovieGenre(genre))
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

//    fun eqMovieGenre(genre: String?): BooleanExpression? {
//        if(StringUtils.isNullOrEmpty(genre)) {
//            return null
//        }
//        return movieEntity.movieGenre.contains(
//            JPAExpressions
//                .selectFrom(movieGenreEntity)
//                .where(movieGenreEntity.name.eq(genre), movieEntity.id.eq(movieGenreEntity.movie.id))
//        )
//    }

    fun eqMovieGenre(genre: String?): BooleanExpression? {
        if (!genre.isNullOrEmpty()) {
            val existsQuery = JPAExpressions.selectOne()
                .from(movieGenreEntity)
                .where(
                    movieGenreEntity.name.eq(genre)
                        .and(movieGenreEntity.movie.id.eq(movieEntity.id))
                )
            return existsQuery.exists()
        }
        return null
    }
}