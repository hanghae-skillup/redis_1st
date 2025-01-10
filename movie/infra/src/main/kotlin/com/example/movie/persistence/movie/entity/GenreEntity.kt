package com.example.movie.persistence.movie.entity

import com.example.movie.domain.movie.model.Genre
import com.example.movie.persistence.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "genre")
class GenreEntity (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    val id: Long,

    @Column(unique = true)
    val name: String,

    val description: String,

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
    fun toDomain(): Genre {
        return Genre(
            id = id,
            name = name,
            description = description,
            createdBy = createdBy,
            createdAt = createdAt,
            updatedBy = updatedBy,
            updatedAt = updatedAt
        )
    }

    companion object {
        fun from(genre: Genre): GenreEntity {
            return GenreEntity(
                id = genre.id,
                name = genre.name,
                description = genre.description,
                createdBy = genre.createdBy,
                createdAt = genre.createdAt,
                updatedBy = genre.updatedBy,
                updatedAt = genre.updatedAt
            )
        }
    }
}