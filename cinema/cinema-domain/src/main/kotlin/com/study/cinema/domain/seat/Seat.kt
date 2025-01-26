package com.study.cinema.domain.seat

import com.study.cinema.domain.theater.Theater
import jakarta.persistence.*

@Entity
class Seat(
    @ManyToOne(fetch = FetchType.LAZY)
    val theater: Theater,
    val positionRow: String,
    val positionColumn: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id = 0L
}