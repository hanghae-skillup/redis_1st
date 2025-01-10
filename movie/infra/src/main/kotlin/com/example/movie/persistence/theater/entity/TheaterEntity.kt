package com.example.movie.persistence.theater.entity

import com.example.movie.domain.theater.model.Theater
import com.example.movie.persistence.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "theater")
class TheaterEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    val id: Long = 0,

    val name: String,

    createdBy: String,
    createdAt: LocalDateTime,
    updatedBy: String,
    updatedAt: LocalDateTime
) : BaseEntity(
    createdBy = createdBy,
    createdAt = createdAt,
    updatedBy = updatedBy,
    updatedAt = updatedAt
) {
    fun toDomain(): Theater {
        return Theater(
            id = id,
            name = name,
            createdBy = createdBy,
            createdAt = createdAt,
            updatedBy = updatedBy,
            updatedAt = updatedAt
        )
    }

    companion object {
        fun from(theater: Theater): TheaterEntity {
            return TheaterEntity(
                id = theater.id,
                name = theater.name,
                createdBy = theater.createdBy,
                createdAt = theater.createdAt,
                updatedBy = theater.updatedBy,
                updatedAt = theater.updatedAt
            )
        }
    }
}