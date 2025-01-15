package com.hanghae.infrastructure.repository;

import com.hanghae.infrastructure.entity.MovieEntity;
import com.hanghae.infrastructure.entity.ScreenEntity;
import com.hanghae.infrastructure.entity.ScreeningScheduleEntity;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    @Sql(scripts = "/sql/ScreeningScheduleTest.sql") // SQL 파일 실행
    @DisplayName("상영시간표 조회")
    void testSaveAndFindAll() {
        // given
        MovieEntity movie = repository.findById(1L).orElseThrow(()->new EntityNotFoundException("영화 조회 실패"));
        ScreenEntity screen = repository2.findById(1L).orElseThrow(()->new EntityNotFoundException("영화 조회 실패"));

        ScreeningScheduleEntity screeningScheduleEntity = ScreeningScheduleEntity.builder()
                .screenEntity(screen)
                .movieEntity(movie)
                .showStartAt(LocalDateTime.of(2024, 10, 15, 11, 11, 11))
                .build();

        // when
        ScreeningScheduleEntity savedScreeningSchedule = repository3.save(screeningScheduleEntity);
        List<ScreeningScheduleEntity> scheduleList = repository3.findAll();

        // then
        assertTrue(scheduleList.size() > 0, "조회된 데이터가 없습니다.");
    }
}
