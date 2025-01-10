package com.redis.cinema.domain

import cinema.domain.Seat
import com.redis.utils.BaseEntity
import jakarta.persistence.*

@Table(name = "cinema")
@Entity
class Cinema(

    @Column(name = "cinema_name")
    val name: String,

    @OneToMany(mappedBy = "cinema", cascade = [CascadeType.ALL], orphanRemoval = true)
    val seats: MutableList<Seat> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id")
    val id: Long? = null
): BaseEntity() {

}