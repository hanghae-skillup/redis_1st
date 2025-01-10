package com.example.movie.persistence.screening.repository

import com.example.movie.domain.screening.model.ScreeningStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ScreeningJpaRepositoryTest {
    @Autowired
    private lateinit var screeningJpaRepository: ScreeningJpaRepository

    @Test
    fun `현재 상영 중인 영화 목록을 조회한다`() {
        // given
        val currentTime = LocalDateTime.now()

        // when
        val movies = screeningJpaRepository.findMoviesNowPlaying(currentTime, ScreeningStatus.SCHEDULED)

        // then
        assertThat(movies).isNotEmpty()
        assertThat(movies).isSortedAccordingTo { a, b ->
            b.releaseDate.compareTo(a.releaseDate)
        }

        // then
        assertThat(movies)
            .isNotEmpty()
            .hasSize(4)
            .isSortedAccordingTo { a, b -> b.releaseDate.compareTo(a.releaseDate) }
            .allMatch { movie ->
                screeningJpaRepository.findScreeningsByMovieId(movie.id, currentTime, ScreeningStatus.SCHEDULED)
                    .any { screening -> screening.screeningTime.isAfter(currentTime) }
            }
        movies.forEach { movie ->
            val movieScreenings = screeningJpaRepository.findScreeningsByMovieId(movie.id, currentTime, ScreeningStatus.SCHEDULED)
            assertThat(movieScreenings)
                .isSortedAccordingTo { a, b -> a.screeningTime.compareTo(b.screeningTime) }
        }
    }

    @Test
    fun `특정 영화의 상영 정보를 조회한다`() {
        // given
        val movieId = 1L
        val currentTime = LocalDateTime.now()

        // when
        val screenings = screeningJpaRepository.findScreeningsByMovieId(movieId, currentTime, ScreeningStatus.SCHEDULED)

        // then
        assertThat(screenings)
            .isNotEmpty()
            .hasSize(2)
            .allMatch { it.movie.id == movieId }
            .isSortedAccordingTo { a, b ->
                a.screeningTime.compareTo(b.screeningTime)
            }
    }
}
