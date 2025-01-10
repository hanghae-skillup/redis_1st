package com.redis.domain

import com.redis.utils.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.BatchSize
import java.time.LocalDateTime

@Table(name = "movie")
@Entity
class Movie(
    @Column(name = "title")
    val title: String,

    @Column(name = "film_ratings")
    @Enumerated(EnumType.STRING)
    val filmRatings: FilmRatings,

    @Column(name = "release_date")
    val releaseDate: LocalDateTime,

    @Column(name = "thumbnail_image_path")
    val thumbnailImagePath: String,

    @Column(name = "running_time")
    val runningTime: Long,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    val genre: MovieGenre,

    @Column(name = "cinema_id")
    val cinemaId: Long,

    @Embedded
    val screeningSchedules: ScreeningSchedules = ScreeningSchedules(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    val id: Long? = null
): BaseEntity() {

}