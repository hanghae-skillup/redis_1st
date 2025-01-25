package com.example.redis.theater.out.persistence.jpa

import com.example.redis.cmmn.BaseEntity
import com.example.redis.movie.out.persistence.jpa.MovieTheaterEntity
import com.example.redis.movie.out.persistence.jpa.Screening
import jakarta.persistence.*
import org.hibernate.annotations.BatchSize

@Table(name = "theater")
@Entity
class TheaterEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    val id: Long? = null,

    @Column(name = "name")
    val name: String,

    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val seats: MutableList<SeatEntity> = mutableListOf(),

    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY, cascade=[CascadeType.ALL], orphanRemoval = true)
    val screening: MutableList<Screening> = mutableListOf(),
): BaseEntity() {}