package com.example.redis.theater.out.persistence

import com.example.redis.cmmn.BaseEntity
import jakarta.persistence.*

@Table(name = "seat")
@Entity
class SeatEntity(

    @Column(name = "seat_row")
    val row: String,

    @Column(name = "seat_col")
    val col: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    val id: Long? = null,
): BaseEntity() {
}