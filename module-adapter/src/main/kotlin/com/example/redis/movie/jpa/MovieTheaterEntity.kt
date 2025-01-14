package com.example.redis.movie

import com.example.redis.BaseEntity
import com.example.redis.theater.SeatEntity
import com.example.redis.theater.TheaterEntity
import jakarta.persistence.*
import org.hibernate.annotations.BatchSize

@Table(name = "movie_theater")
@Entity
class MovieTheaterEntity(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    val movie: MovieEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    val theater: TheaterEntity,

    @BatchSize(size = 1_000)
    @OneToMany(mappedBy = "movieTheater", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val seats: MutableList<SeatEntity> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    ): BaseEntity() {
}