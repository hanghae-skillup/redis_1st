package com.example.movie.persistence.movie.model

import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.movie.model.Rating
import com.example.movie.persistence.genre.model.GenreEntity
import com.example.movie.persistence.common.BaseEntity
import com.example.movie.persistence.screening.model.ScreeningEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "movie")
class MovieEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", columnDefinition = "INT UNSIGNED")
    val id: Long,

    val title: String,

    @Enumerated(EnumType.STRING)
    val rating: Rating,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", columnDefinition = "INT UNSIGNED", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val genre: GenreEntity,

    val releaseDate: LocalDate,
    val thumbnailUrl: String,
    val runningTime: Int,

    @OneToMany(mappedBy = "movie")
    val screenings: MutableList<ScreeningEntity> = mutableListOf(),
) : BaseEntity() {
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
            )
        }
    }
}