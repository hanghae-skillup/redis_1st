package io.github.jehwanyoo.redis_1st.entity

import io.github.jehwanyoo.redis_1st.model.ShowTime
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "show_time")
class ShowTimeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long, // 고유 ID

    @Column(nullable = false)
    val movieId: Long, // 영화 고유 ID

    @Column(nullable = false)
    val screenId: Long, // 스크린 고유 ID

    @Column(nullable = false)
    val startTime: LocalDateTime // 시작 시간
) {
    fun toDomain(): ShowTime = ShowTime(
        id = id,
        movieId = movieId,
        screenId = screenId,
        startTime = startTime
    )
}