package com.example.movie.persistence.movie.entity

import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.movie.model.Rating
import com.example.movie.persistence.common.BaseEntity
import com.example.movie.persistence.screening.model.ScreeningEntity
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "movie")
class MovieEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    val id: Long,

    val title: String,

    @Enumerated(EnumType.STRING)
    val rating: Rating,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val genre: GenreEntity,

    val releaseDate: LocalDate,
    val thumbnailUrl: String,
    val runningTime: Int,

    @OneToMany
    @JoinColumn(name = "screening_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val screenings: MutableList<ScreeningEntity> = mutableListOf(),

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
    fun toDomain(): Movie {
        return Movie(
            id = id,
            title = title,
            rating = rating,
            genre = genre.toDomain(),
            releaseDate = releaseDate,
            thumbnailUrl = thumbnailUrl,
            runningTime = runningTime,
            createdBy = createdBy,
            createdAt = createdAt,
            updatedBy = updatedBy,
            updatedAt = updatedAt
        )
    }

    companion object {
        fun from(movie: Movie, genreEntity: GenreEntity): MovieEntity {
            return MovieEntity(
                id = movie.id,
                title = movie.title,
                rating = movie.rating,
                genre = genreEntity,
                releaseDate = movie.releaseDate,
                thumbnailUrl = movie.thumbnailUrl,
                runningTime = movie.runningTime,
                createdBy = movie.createdBy,
                createdAt = movie.createdAt,
                updatedBy = movie.updatedBy,
                updatedAt = movie.updatedAt
            )
        }
    }
}