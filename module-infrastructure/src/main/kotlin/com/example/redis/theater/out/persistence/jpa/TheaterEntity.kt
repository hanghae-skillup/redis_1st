package com.example.redis.theater.out.persistence.jpa

import com.example.redis.cmmn.BaseEntity
import com.example.redis.movie.out.persistence.jpa.MovieTheaterEntity
import jakarta.persistence.*
import org.hibernate.annotations.BatchSize

@Table(name = "theater")
@Entity
class TheaterEntity(

    @Column(name = "name")
    val name: String,

    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val seats: MutableList<SeatEntity> = mutableListOf(),

    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val movieTheaters: MutableList<MovieTheaterEntity> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    val id: Long? = null
): BaseEntity() {
}