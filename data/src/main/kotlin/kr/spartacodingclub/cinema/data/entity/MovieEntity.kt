package kr.spartacodingclub.cinema.data.entity

import jakarta.persistence.*
import kr.spartacodingclub.cinema.core.domain.Genre
import kr.spartacodingclub.cinema.core.domain.Movie
import kr.spartacodingclub.cinema.core.domain.MovieRating
import java.time.LocalDate

@Entity
@Table(name = "movie")
class MovieEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val title: String,

    @Enumerated(EnumType.STRING)
    val rating: MovieRating,

    val releaseDate: LocalDate,
    val thumbnailUrl: String,
    val runningTime: Int,

    @Enumerated(EnumType.STRING)
    val genre: Genre
) : BaseEntity() {
    fun toDomain() = Movie(
        id = id,
        title = title,
        rating = rating,
        releaseDate = releaseDate,
        thumbnailUrl = thumbnailUrl,
        runningTime = runningTime,
        genre = genre
    )
}
