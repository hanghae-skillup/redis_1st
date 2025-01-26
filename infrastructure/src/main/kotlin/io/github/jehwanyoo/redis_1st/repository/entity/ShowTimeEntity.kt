package io.github.jehwanyoo.redis_1st.repository.entity

import io.github.jehwanyoo.redis_1st.model.ShowTime
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "show_time")
class ShowTimeEntity(
    @Id
    @GeneratedValue
    val id: UUID? = null,           // 고유 ID

    @Column(nullable = false)
    val startTime: LocalDateTime,   // 시작 시간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    val movie: MovieEntity,         // 연관된 영화

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id", nullable = false)
    val screen: ScreenEntity,       // 연관된 상영관
) {
    fun toDomain(): ShowTime {
        if (id == null || movie.id == null || screen.id == null) {
            throw IllegalStateException("Entity is not persisted yet: $this")
        }

        return ShowTime(
            id = id,
            movieId = movie.id,
            screenId = screen.id,
            startTime = startTime,
        )
    }
}