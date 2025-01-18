package com.example.redis.movie.out.persistence.jpa

import com.example.redis.movie.out.persistence.jpa.QMovieEntity.*
import com.example.redis.movie.out.persistence.jpa.QMovieGenreEntity.*
import com.example.redis.movie.out.persistence.jpa.QMovieTheaterEntity.*
import com.example.redis.movie.query.MovieProjection
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.util.StringUtils
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
@Repository
class MovieRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
): MovieRepositoryCustom {
    override fun findByOrderByReleaseDateDesc(title: String?, genre: String?): MutableList<MovieProjection> {
        return queryFactory.select(
            Projections.constructor(MovieProjection::class.java,
                movieEntity.id,
                movieEntity.title,
                movieEntity.runningTime,
                movieEntity.releaseDate,
                movieEntity.thumbnailImagePath,
                movieEntity.filmRatings,
                movieGenreEntity.name,
                movieTheaterEntity.theater.name,
                movieEntity.createAt,
                movieEntity.updateAt,
            ))
            .from(movieEntity)
            .join(movieEntity.movieGenre, movieGenreEntity)
            .fetchJoin()
            .where(eqTitle(title), eqMovieGenre(genre))
            .orderBy(movieEntity.releaseDate.desc())
            .fetch()
    }

    fun eqTitle(title: String?): BooleanExpression? {
        return if (StringUtils.isNullOrEmpty(title)) null else movieEntity.title.eq(title)
    }

    fun eqMovieGenre(genre: String?): BooleanExpression? {
        return if (StringUtils.isNullOrEmpty(genre)) null else movieGenreEntity.name.eq(genre)
    }
}