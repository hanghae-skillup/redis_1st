package com.redis.domain

import com.redis.domain.Cinema
import com.redis.utils.BaseEntity
import jakarta.persistence.*

@Table(name = "seat")
@Entity
class Seat(

    @Column(name = "seat_row")
    val row: String,

    @Column(name = "seat_col")
    val col: String,

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    val cinema: Cinema,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    val id: Long? = null
): BaseEntity() {

}