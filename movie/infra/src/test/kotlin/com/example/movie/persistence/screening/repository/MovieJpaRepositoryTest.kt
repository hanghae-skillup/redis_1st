package com.example.movie.persistence.screening.repository

import com.example.movie.domain.movie.repository.MovieRepository
import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.persistence.movie.repository.MovieJpaRepository
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
class MovieJpaRepositoryTest {
    @Autowired
    private lateinit var movieJpaRepository: MovieJpaRepository

    @Test
    fun `현재 상영 중인 영화 목록을 조회한다`() {
        // given
        val currentTime = LocalDateTime.now()

        // when
        val movies = movieJpaRepository.findMoviesNowPlaying(currentTime, ScreeningStatus.SCHEDULED)

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
    }
}