package com.example.demo.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "movies")
data class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 255)
    val title: String,

    @Enumerated(EnumType.STRING)
    val rating: Rating,

    val releaseDate: LocalDateTime,

    val thumbnailUrl: String,

    val runningTime: Int,

    val genre: String
)

enum class Rating {
    GENERAL, PG13, R, NC17
} 