package io.github.jehwanyoo.redis_1st.model

data class Screen(
    val id: Long,       // 고유 ID
    val name: String,   // 상영관 이름
    val row: Int,       // 좌석 행 수
    val column: Int,    // 좌석 열 수
)
