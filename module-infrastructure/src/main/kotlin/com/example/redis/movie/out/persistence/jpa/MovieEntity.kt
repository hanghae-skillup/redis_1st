package com.example.redis.movie.out.persistence.jpa

import com.example.redis.cmmn.BaseEntity
import com.example.redis.movie.FilmRatings
import com.querydsl.core.annotations.QueryEntity
import jakarta.persistence.*
import org.hibernate.annotations.BatchSize
import java.time.LocalDateTime

@Entity
@QueryEntity
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

//    @ElementCollection
//    @CollectionTable(name = "movie_genre", joinColumns = [JoinColumn(name = "movie_id")])
//    @Column(name = "name")0
    @BatchSize(size = 1_000)
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var movieGenre: MutableList<MovieGenreEntity> = mutableListOf(),

    @BatchSize(size = 1_000)
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val movieTheaters: MutableList<MovieTheaterEntity> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    val id: Long? = null
): BaseEntity() {
}