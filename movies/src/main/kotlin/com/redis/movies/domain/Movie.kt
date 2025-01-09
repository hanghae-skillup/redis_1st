package com.redis.movies.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "movie")
@Entity
class Movie(
    @Column(name = "title")
    val title: String,

    @Column(name = "film_ratings")
    @Enumerated(EnumType.STRING)
    val filmRatings: String,

    @Column(name = "release_date")
    val releaseDate: LocalDateTime,

    @Column(name = "thumbnail_image_path")
    val thumbnailImagePath: String,

    @Column(name = "running_time")
    val runningTime: Long,

    @OneToOne(mappedBy = "movie", cascade = [CascadeType.ALL], orphanRemoval = true)
    val genre: MovieGenre,

    @Column(name = "theater_name")
    val theaterName: String,

    val screeningSchedules: ScreeningSchedules = ScreeningSchedules(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {

}