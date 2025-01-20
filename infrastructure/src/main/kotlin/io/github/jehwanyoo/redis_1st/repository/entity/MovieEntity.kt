package io.github.jehwanyoo.redis_1st.repository.entity

import io.github.jehwanyoo.redis_1st.model.Movie
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(
    name = "movie",
    indexes = [Index(name = "idx_movie_title", columnList = "title")]
)
class MovieEntity(
    @Id
    @GeneratedValue
    val id: UUID? = null,               // UUID

    @Column(nullable = false)
    val title: String,                  // 영화 제목

    @Column(nullable = false)
    val releaseDate: LocalDate,         // 개봉일

    @Column(nullable = false)
    val thumbnailUrl: String,            // 썸네일 URL

    @Column(nullable = false)
    val runtimeMinutes: Int,             // 러닝 타임 (분)

    @Column(nullable = false)
    val genre: String,                   // 장르

    @Column(nullable = false)
    val rating: String,                   // 영상물 등급

    @OneToMany(mappedBy = "movie", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val showTimes: List<ShowTimeEntity> = emptyList() // 상영 시간 리스트
) {
    fun toDomain(): Movie {
        if (id == null) {
            throw IllegalStateException("Entity is not persisted yet: $this")
        }

        // 상영관 별로 집계
        val screensGroupedByShowTimes = showTimes.groupBy { it.screen }
        val screens = screensGroupedByShowTimes.map { (screen) -> screen.toDomain() }

        return Movie(
            id = id,
            title = title,
            releaseDate = releaseDate,
            thumbnailUrl = thumbnailUrl,
            runtimeMinutes = runtimeMinutes,
            genre = genre,
            rating = rating,
            screens = screens,
        )
    }
}