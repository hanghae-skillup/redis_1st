package com.redis.movies.domain

import jakarta.persistence.*

@Table(name = "movie_genre")
@Entity
data class MovieGenre(

    @Column(name = "name")
    val name: String,

    @OneToOne
    @JoinColumn(name = "movie_id")
    val movie: Movie,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_genre_id")
    val id: Long? = null
) {
}