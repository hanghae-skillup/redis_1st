package com.redis.domain

import com.redis.utils.BaseEntity
import jakarta.persistence.*

@Table(name = "movie_genre")
@Entity
class MovieGenre(

    @Column(name = "name")
    val name: String,

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "movie_id")
//    val movie: Movie,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_genre_id")
    val id: Long? = null
): BaseEntity() {
}