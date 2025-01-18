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
    @Column(name = "screening_id", columnDefinition = "INT UNSIGNED")
    val id: Long = 0,

    @Column(name = "movie_id", columnDefinition = "INT UNSIGNED")
    val movieId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", columnDefinition = "INT UNSIGNED", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val theater: TheaterEntity,

    val screeningTime: LocalDateTime,

    val screeningEndTime: LocalDateTime,

    @Enumerated(EnumType.STRING)
    val status: ScreeningStatus,
) : BaseEntity() {
    fun toDomain(): Screening {
        return Screening(
            id = id,
            movieId = movieId,
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
}