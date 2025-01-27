package com.study.cinema.domain.schedule

import com.study.cinema.domain.cinema.Cinema
import com.study.cinema.domain.movie.Movie
import com.study.cinema.domain.theater.Theater
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.time.ZonedDateTime

@Entity
@Table(name = "movie_schedule")
class MovieSchedule(
    startAt: ZonedDateTime,
    endAt: ZonedDateTime,
    movie: Movie,
    theater: Theater
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Comment("상영 시작 시간")
    var startAt: ZonedDateTime = startAt
        protected set

    @Comment("상영 종료 시간")
    var endAt: ZonedDateTime = endAt
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    val movie: Movie = movie

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    val theater: Theater = theater

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    val cinema: Cinema = theater.cinema
}