package com.hanghae.infrastructure.repository;

import com.hanghae.infrastructure.entity.MovieEntity;
import com.hanghae.infrastructure.entity.ScreenEntity;
import com.hanghae.infrastructure.entity.ScreeningScheduleEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test") // application-test.properties 사용
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ScreeningScheduleRepositoryTest {
    @Autowired
    private MovieRepositoryJpa repository;

    @Autowired
    private ScreenRepositoryJpa repository2;

    @Autowired
    private ScreeningScheduleRepositoryJpa repository3;

    @Test
    @DisplayName("상영시간표 조회")
    void testSaveAndFindAll() {
        // given
        MovieEntity movie = MovieEntity.builder()
                .title("테스트제목")
                .ratingId("111")
                .releaseDate(LocalDate.of(2025, 01, 10))
                .runningTime(11L)
                .genreId("333")
                .build();

        ScreenEntity screen = ScreenEntity.builder()
                .screenName("상영관이름")
                .build();

        // when
        MovieEntity savedMovie = repository.save(movie);
        ScreenEntity savedScreen = repository2.save(screen);

        ScreeningScheduleEntity screeningScheduleEntity = ScreeningScheduleEntity.builder()
                .screenEntity(screen)
                .movieEntity(movie)
                .showStartDatetime(LocalDateTime.of(2024, 10, 15, 11, 11, 11))
                .build();
        ScreeningScheduleEntity savedScreeningSchedule = repository3.save(screeningScheduleEntity);

        List<ScreeningScheduleEntity> scheduleList = repository3.findAll();

        // then
        assertTrue(scheduleList.size() > 0, "조회된 데이터가 없습니다.");
    }
}
