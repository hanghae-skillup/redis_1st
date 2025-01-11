package kr.spartacodingclub.cinema.data.entity

import jakarta.persistence.*
import kr.spartacodingclub.cinema.core.domain.Theater

@Entity
@Table(name = "theater")
class TheaterEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    
    val name: String
) {
    fun toDomain() = Theater(
        id = id,
        name = name
    )
}
