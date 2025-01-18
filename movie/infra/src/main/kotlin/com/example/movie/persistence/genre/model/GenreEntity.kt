package com.example.movie.persistence.genre.model

import com.example.movie.domain.movie.model.Genre
import com.example.movie.persistence.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "genre")
class GenreEntity (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", columnDefinition = "INT UNSIGNED")
    val id: Long,

    @Column(unique = true)
    val name: String,

    val description: String,
) : BaseEntity(){
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
}