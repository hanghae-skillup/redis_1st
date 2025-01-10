package entity

import java.time.LocalDateTime
import java.util.*

data class ShowTime(
    val id: UUID,                   // 고유 ID
    val movieId: UUID,              // 영화 고유 ID
    val screenId: UUID,             // 스크린 고유 ID
    val startTime: LocalDateTime    // 시작 시간
)
