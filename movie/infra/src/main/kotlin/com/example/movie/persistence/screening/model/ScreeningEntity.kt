package com.example.movie.persistence.screening.model

import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.persistence.common.BaseEntity
import com.example.movie.persistence.movie.model.MovieEntity
import com.example.movie.persistence.theater.entity.TheaterEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "screening")
class ScreeningEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screening_id")
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val movie: MovieEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val theater: TheaterEntity,

    val screeningTime: LocalDateTime,

    val screeningEndTime: LocalDateTime,

    @Enumerated(EnumType.STRING)
    val status: ScreeningStatus,
) : BaseEntity() {
    fun toDomain(): Screening {
        return Screening(
            id = id,
            movie = movie.toDomain(),
            theater = theater.toDomain(),
            screeningTime = screeningTime,
            screeningEndTime = screeningEndTime,
            status = status,
            createdBy = createdBy,
            createdAt = createdAt,
            updatedBy = updatedBy,
            updatedAt = updatedAt
        )
    }

    companion object {
        fun from(screening: Screening, movie: MovieEntity, theater: TheaterEntity): ScreeningEntity {
            return ScreeningEntity(
                id = screening.id,
                movie = movie,
                theater = theater,
                screeningTime = screening.screeningTime,
                screeningEndTime = screening.screeningEndTime,
                status = screening.status,
            )
        }
    }
}