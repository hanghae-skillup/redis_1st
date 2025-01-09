package com.redis.movies.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "screening_schedule")
@Entity
data class ScreeningSchedule(

    @Column(name = "start_time")
    val startTime: LocalDateTime,

    @Column(name = "end_time")
    val endTime: LocalDateTime,

    @ManyToOne()
    @JoinColumn(name = "movie_id")
    val movie: Movie? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {
}