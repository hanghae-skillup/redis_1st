package com.study.cinema.domain.movie

import jakarta.persistence.Column
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import org.hibernate.annotations.Comment
import java.time.LocalDate

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Movie(
    title: String,
    genre: Genre,
    movieRating: MovieRating,
    releaseDate: LocalDate,
    runningTimeMinutes: Int,
    posterUrl: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Comment("제목")
    var title: String = title
        protected set

    @Comment("장르")
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    var genre: Genre = genre
        protected set

    @Comment("영상물 등급")
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    var movieRating: MovieRating = movieRating
        protected set

    @Comment("개봉일")
    var releaseDate: LocalDate = releaseDate
        protected set

    @Comment("러닝 타임 (분)")
    var runningTimeMinutes: Int = runningTimeMinutes
        protected set

    @Comment("포스터 URL")
    var thumbnailUrl: String = posterUrl
        protected set
}