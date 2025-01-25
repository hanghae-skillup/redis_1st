package com.example.redis.movie.out.persistence.jpa

import com.example.redis.cmmn.BaseEntity
import com.example.redis.theater.out.persistence.jpa.TheaterEntity
import jakarta.persistence.*
import org.hibernate.annotations.BatchSize

@Table(name = "movie_theater")
@Entity
class MovieTheaterEntity(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    val movie: MovieEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    val theater: TheaterEntity,

    @OneToMany(mappedBy = "movieTheater", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val screeningSchedules: MutableList<ScreeningScheduleEntity> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_theater_id")
    val id: Long? = null,
    ): BaseEntity() {
}