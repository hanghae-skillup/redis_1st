package com.example.redis.movie

import com.example.redis.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.BatchSize
import java.time.LocalDateTime

@Entity
@Table(name = "movie")
class MovieEntity(

    @Column(name = "title")
    val title: String,

    @Column(name = "thumbnail_image_path")
    val thumbnailImagePath: String,

    @Column(name = "running_time")
    val runningTime: Long,

    @Column(name = "release_date")
    val releaseDate: LocalDateTime,

    @Enumerated(EnumType.STRING)
    @Column(name = "film_ratings")
    val filmRatings: FilmRatings,

    @BatchSize(size = 1_000)
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val movieGenre: MovieGenreEntity,

    @BatchSize(size = 1_000)
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val movieTheaters: MutableList<MovieTheaterEntity> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private val id: Long? = null
): BaseEntity() {
}