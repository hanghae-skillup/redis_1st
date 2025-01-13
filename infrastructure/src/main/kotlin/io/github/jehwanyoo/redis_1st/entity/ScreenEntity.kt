package io.github.jehwanyoo.redis_1st.entity

import io.github.jehwanyoo.redis_1st.model.Screen
import jakarta.persistence.*

@Entity
@Table(name = "screen")
class ScreenEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long, // 고유 ID

    @Column(nullable = false)
    val name: String, // 상영관 이름

    @Column(name = "seat_row", nullable = false)
    val row: Int, // 좌석 행 수

    @Column(name = "seat_column", nullable = false)
    val column: Int // 좌석 열 수
) {
    fun toDomain(): Screen = Screen(
        id = id,
        name = name,
        row = row,
        column = column
    )
}