package com.example.redis.movie

import com.example.redis.BaseEntity
import com.example.redis.movie.MovieEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "screening_schedule")
@Entity
class ScreeningScheduleEntity(

    @Column(name = "start_time")
    val startTime: LocalDateTime,

    @Column(name = "end_time")
    val endTime: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    val movie: MovieEntity,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screening_schedule_id")
    val id: Long? = null
): BaseEntity() {
}