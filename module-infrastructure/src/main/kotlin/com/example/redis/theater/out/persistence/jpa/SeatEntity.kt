package com.example.redis.theater.out.persistence.jpa

import com.example.redis.cmmn.BaseEntity
import com.example.redis.movie.MovieTheater
import jakarta.persistence.*

@Table(name = "seat")
@Entity
class SeatEntity(

    @Column(name = "seat_row")
    val row: String,

    @Column(name = "seat_col")
    val col: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    val theater: TheaterEntity,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    val id: Long? = null,
): BaseEntity() {
}