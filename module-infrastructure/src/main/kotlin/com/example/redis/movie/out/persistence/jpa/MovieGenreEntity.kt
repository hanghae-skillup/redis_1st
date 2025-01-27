package com.example.redis.movie.out.persistence.jpa

import com.example.redis.cmmn.BaseEntity
import jakarta.persistence.*

@Table(name = "movie_genre")
@Entity
class MovieGenreEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_genre_id")
    val id: Long? = null,

    @Column(name = "name")
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    val movie: MovieEntity,
): BaseEntity() {}