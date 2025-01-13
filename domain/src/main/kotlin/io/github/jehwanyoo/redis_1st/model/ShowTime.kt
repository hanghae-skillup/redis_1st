package io.github.jehwanyoo.redis_1st.model

import java.time.LocalDateTime

data class ShowTime(
    val id: Long,                   // 고유 ID
    val movieId: Long,              // 영화 고유 ID
    val screenId: Long,             // 스크린 고유 ID
    val startTime: LocalDateTime    // 시작 시간
)
