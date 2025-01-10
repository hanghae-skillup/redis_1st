package com.redis.domain

import com.redis.utils.BaseEntity
import jakarta.persistence.*

@Table(name = "cinema")
@Entity
class Cinema(

    @Column(name = "name")
    val name: String,

    @OneToMany(mappedBy = "cinema", cascade = [CascadeType.ALL], orphanRemoval = true)
    val seats: MutableList<Seat> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id")
    val id: Long? = null
): BaseEntity() {

}