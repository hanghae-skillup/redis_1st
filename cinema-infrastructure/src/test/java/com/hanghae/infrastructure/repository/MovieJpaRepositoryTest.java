package com.hanghae.infrastructure.repository;

import com.hanghae.infrastructure.entity.MovieEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test") // application-test.properties 사용
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieJpaRepositoryTest {

    @Autowired
    private MovieJpaRepository repository;

    @Test
    @DisplayName("영화 조회")
    void testSaveAndFindById() {
        // given
        MovieEntity movie = MovieEntity.builder()
                .title("테스트제목")
                .ratingId("111")
                .releaseDate(LocalDate.of(2025, 01, 10))
                .runningTime(11L)
                .genreId("333")
                .build();

        // when
        MovieEntity savedMovie = repository.save(movie);
        List<MovieEntity> findMovie = repository.findAll();

        // then
        //assertThat(findMovie).isPresent();
        assertThat(findMovie.get(0).getTitle()).isEqualTo("테스트제목");
    }
}
