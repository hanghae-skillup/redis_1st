package com.example.demo.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "screenings")
data class Screening(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    val movie: Movie,

    val cinemaName: String,

    val startTime: LocalDateTime
) 