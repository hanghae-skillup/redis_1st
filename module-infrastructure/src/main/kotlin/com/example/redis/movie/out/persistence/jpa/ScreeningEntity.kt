package com.example.redis.movie.out.persistence.jpa

import com.example.redis.cmmn.BaseEntity
import com.example.redis.reserve.out.persistence.jpa.ReservationEntity
import com.example.redis.theater.out.persistence.jpa.TheaterEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "screening")
@Entity
class ScreeningEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screening_id")
    val id: Long? = null,

    @Column(name = "start_time")
    val startTime: LocalDateTime,

    @Column(name = "end_time")
    val endTime: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    val movie: MovieEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    val theater: TheaterEntity,

    @OneToMany(mappedBy = "screening", fetch = FetchType.LAZY)
    val reservations: MutableList<ReservationEntity> = mutableListOf()
): BaseEntity() {
}