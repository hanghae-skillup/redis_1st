package com.movie.storage.movie.repository;

import com.movie.common.enums.Genre;
import com.movie.domain.movie.dto.command.ScheduleCommand;
import com.movie.domain.movie.dto.info.ScheduleInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ScheduleRepositoryImplTest {

    @Autowired
    private ScheduleRepositoryImpl sut;

    @Test
    void givenTheaterId_whenSearchingSchedules_thenReturnSchedules() {
        // given
        Long theaterId = 1L;

        // when
        List<ScheduleInfo.Get> schedules = sut.getSchedules(theaterId);

        // then
        assertThat(schedules)
                .isNotEmpty()
                .extracting("id")
                .containsExactly(1L);
    }

    @Test
    void givenMovieTitleAndGenre_whenSearchingSchedules_thenReturnSchedules() {
        // given
        String title = "범죄도시";
        Genre genre = Genre.ACTION;
        ScheduleCommand.Search search = ScheduleCommand.Search.of(title, genre);

        // when
        List<ScheduleInfo.Get> schedules = sut.getSchedules(search);

        // then
        assertThat(schedules)
                .isNotEmpty()
                .extracting("id")
                .containsExactly(1L);
    }

}