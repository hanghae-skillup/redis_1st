package com.example.redis.movie.out.persistence.jpa

import com.example.redis.cmmn.BaseEntity
import com.example.redis.movie.FilmRatings
import com.example.redis.movie.Screening
import com.querydsl.core.annotations.QueryEntity
import jakarta.persistence.*
import org.hibernate.annotations.BatchSize
import java.time.LocalDateTime

@Entity
@QueryEntity
@Table(name = "movie")
class MovieEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    val id: Long? = null,

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

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var movieGenre: MutableList<MovieGenreEntity> = mutableListOf(),

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val screening: MutableList<ScreeningEntity> = mutableListOf()
): BaseEntity() {}