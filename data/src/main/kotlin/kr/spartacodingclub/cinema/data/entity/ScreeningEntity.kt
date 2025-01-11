package kr.spartacodingclub.cinema.data.entity

import jakarta.persistence.*
import kr.spartacodingclub.cinema.core.domain.Screening
import java.time.LocalDateTime

@Entity
@Table(name = "screening")
class ScreeningEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "movie_id",
        foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    val movie: MovieEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "theater_id",
        foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    val theater: TheaterEntity,

    val startTime: LocalDateTime
) : BaseEntity() {
    fun toDomain() = Screening(
        id = id,
        movieId = movie.id,
        theater = theater.toDomain(),  // theater 엔티티를 도메인 객체로 변환
        startTime = startTime
    )
}
