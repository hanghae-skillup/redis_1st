package io.github.jehwanyoo.redis_1st.entity

import io.github.jehwanyoo.redis_1st.model.Movie
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "movie")
class MovieEntity(
    @Id
    @GeneratedValue
    val id: UUID,                       // UUID

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
    val rating: String                   // 영상물 등급
) {
    fun toDomain(): Movie = Movie(
        id = id,
        title = title,
        releaseDate = releaseDate,
        thumbnailUrl = thumbnailUrl,
        runtimeMinutes = runtimeMinutes,
        genre = genre,
        rating = rating
    )
}