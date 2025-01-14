package com.example.redis.movie.out.persistence

import com.example.redis.cmmn.BaseEntity
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
    @JoinColumn(name = "movie_theater_id")
    val movieTheater: MovieTheaterEntity,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screening_schedule_id")
    val id: Long? = null
): BaseEntity() {
}