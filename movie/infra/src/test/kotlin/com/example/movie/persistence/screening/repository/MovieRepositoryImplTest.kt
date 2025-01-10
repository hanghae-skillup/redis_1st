package com.example.movie.persistence.screening.repository

import com.example.movie.domain.movie.model.Rating
import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.persistence.common.BaseEntity
import com.example.movie.persistence.genre.model.GenreEntity
import com.example.movie.persistence.movie.model.MovieEntity
import com.example.movie.persistence.movie.repository.MovieJpaRepository
import com.example.movie.persistence.movie.repository.MovieRepositoryImpl
import com.example.movie.persistence.screening.model.ScreeningEntity
import com.example.movie.persistence.theater.entity.TheaterEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import java.time.LocalDate
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class MovieRepositoryImplTest {

    @Mock
    private lateinit var screeningJpaRepository: ScreeningJpaRepository
    @Mock
    private lateinit var movieJpaRepository: MovieJpaRepository

    @InjectMocks
    private lateinit var movieRepository: MovieRepositoryImpl

    @Test
    fun `findAllNowPlayingWithMovieAndTheater 테스트`() {
        // given
        val currentTime = LocalDateTime.of(2024, 1, 2, 12, 0)

        val genre = createGenre()
        val movie = createMovie(genre)
        val theater = createTheater()

        whenever(movieJpaRepository.findMoviesNowPlaying(currentTime, ScreeningStatus.SCHEDULED))
            .thenReturn(listOf(movie))

        whenever(screeningJpaRepository.findScreeningsByMovieId(movie.id, currentTime, ScreeningStatus.SCHEDULED))
            .thenReturn(
                listOf(
                    createScreening(1L, currentTime, movie, theater),
                    createScreening(2L, currentTime.plusHours(3), movie, theater)
                )
            )

        // when
        val result = movieRepository.findAllNowPlayingWithMovieAndTheater(currentTime)

        // then
        assertThat(result).hasSize(1)
        val (movieResult, screenings) = result.entries.first()
        assertThat(screenings).hasSize(2)

        assertThat(movieResult.id).isEqualTo(movie.id)
        assertThat(screenings).allMatch({it.movie.id == movie.id})
    }

    private fun createGenre() = GenreEntity(
        id = 1L,
        name = "action",
        description = "thrilling action"
    ).apply {
        setAuditField()
    }

    private fun createMovie(genre: GenreEntity) = MovieEntity(
        id = 1L,
        title = "title1",
        rating = Rating.ALL,
        genre = genre,
        releaseDate = LocalDate.of(2024,1,1),
        thumbnailUrl = "https://image.testdb/image.jpg",
        runningTime = 120
    ).apply {
        setAuditField()
    }

    private fun createTheater() = TheaterEntity(
        id = 1L,
        name = "cgv"
    ).apply {
        setAuditField()
    }

    private fun createScreening(id: Long, currentTime: LocalDateTime, movie: MovieEntity, theater: TheaterEntity) = ScreeningEntity(
        id = id,
        movie = movie,
        theater = theater,
        screeningTime = currentTime.plusHours(1),
        screeningEndTime = currentTime.plusHours(3),
        status = ScreeningStatus.SCHEDULED,
    ).apply {
        setAuditField()
    }

    private fun BaseEntity.setAuditField() {
        val field = BaseEntity::class.java.getDeclaredField("createdBy")
        field.isAccessible = true
        field.set(this, "system")

        val createdAtField = BaseEntity::class.java.getDeclaredField("createdAt")
        createdAtField.isAccessible = true
        createdAtField.set(this, LocalDateTime.now())

        val updatedByField = BaseEntity::class.java.getDeclaredField("updatedBy")
        updatedByField.isAccessible = true
        updatedByField.set(this, "system")

        val updatedAtField = BaseEntity::class.java.getDeclaredField("updatedAt")
        updatedAtField.isAccessible = true
        updatedAtField.set(this, LocalDateTime.now())
    }
}