package com.movie.domain.movie;

import com.movie.common.enums.Genre;
import com.movie.domain.movie.dto.command.ScheduleCommand;
import com.movie.domain.movie.dto.info.MovieInfo;
import com.movie.domain.movie.dto.info.ScheduleInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @InjectMocks
    private ScheduleService sut;

    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private ScheduleRedisRepository scheduleRedisRepository;

    @DisplayName("영화관 id를 이용하여, 스케줄 목록을 조회한다.")
    @Test
    void givenTheaterId_whenSearchingSchedules_thenReturnSchedules() {
        //given
        Long theaterId = 1L;
        given(scheduleRepository.getSchedules(theaterId)).willReturn(getSchedules());

        List<ScheduleInfo.Get> schedules = sut.getSchedules(theaterId);

        assertThat(schedules)
                .isNotNull()
                .extracting("id")
                .containsExactly(1L, 2L, 3L);
    }

    @DisplayName("영화명과 장르를 이용하여, 스케줄 목록을 조회한다.")
    @Test
    void givenMovieNameAndGenre_whenSearchingSchedules_thenReturnSchedules() {
        String title = "범죄도시";
        Genre genre = Genre.ACTION;
        ScheduleCommand.Search search = ScheduleCommand.Search.of(title, genre);
        given(scheduleRepository.getSchedules(search)).willReturn(getSchedules());

        List<ScheduleInfo.Get> schedules = sut.getSchedules(search);

        assertThat(schedules)
                .isNotNull()
                .extracting("id")
                .containsExactly(1L, 2L, 3L);
    }

    @DisplayName("영화명과 장르를 이용하여, 인메모리 캐시에 저장된 스케줄 목록을 조회한다.")
    @Test
    void givenMovieNameAndGenre_whenSearchingSchedules_thenReturnSchedulesFromInMemoryCache() {
        String title = "범죄도시";
        Genre genre = Genre.ACTION;
        ScheduleCommand.Search search = ScheduleCommand.Search.of(title, genre);
        given(scheduleRepository.getSchedules(search)).willReturn(getSchedules());

        List<ScheduleInfo.Get> schedules = sut.getSchedulesAsCached(search);

        assertThat(schedules)
                .isNotNull()
                .extracting("id")
                .containsExactly(1L, 2L, 3L);
    }

    @DisplayName("영화명과 장르를 이용하여, Redis 캐시에 저장된 스케줄 목록을 조회한다.")
    @Test
    void givenMovieNameAndGenre_whenSearchingSchedules_thenReturnSchedulesFromRedisCache() {
        String title = "범죄도시";
        Genre genre = Genre.ACTION;
        ScheduleCommand.Search search = ScheduleCommand.Search.of(title, genre);

        given(scheduleRedisRepository.find(search)).willReturn(getSchedules());

        List<ScheduleInfo.Get> schedules = sut.getSchedulesByRedis(search);

        assertThat(schedules)
                .isNotEmpty()
                .extracting("id")
                .containsExactly(1L, 2L, 3L);

        verify(scheduleRedisRepository, times(1)).find(search);
    }

    @DisplayName("영화명과 장르를 이용하여, Redis 캐시에 저장된 스케줄이 없다면 DB에서 조회한 스케줄 목록을 반환한다.")
    @Test
    void givenMovieNameAndGenre_whenUnableToSearchSchedulesInRedis_thenReturnsSchedulesFromDB() {
        String title = "범죄도시";
        Genre genre = Genre.ACTION;
        ScheduleCommand.Search search = ScheduleCommand.Search.of(title, genre);

        given(scheduleRedisRepository.find(search)).willReturn(List.of());

        given(scheduleRepository.getSchedules(search)).willReturn(getSchedules());
        doNothing().when(scheduleRedisRepository).save(search, getSchedules());

        List<ScheduleInfo.Get> schedules = sut.getSchedulesByRedis(search);

        assertThat(schedules)
                .isNotEmpty()
                .extracting("id")
                .containsExactly(1L, 2L, 3L);

        verify(scheduleRedisRepository, times(1)).find(search);
        verify(scheduleRepository, times(1)).getSchedules(search);
        verify(scheduleRedisRepository, times(1)).save(search, getSchedules());
    }

    private List<ScheduleInfo.Get> getSchedules() {
        return List.of(
                ScheduleInfo.Get.of(1L, MovieInfo.Get.of(1L, "범죄도시", Genre.ACTION)),
                ScheduleInfo.Get.of(2L, MovieInfo.Get.of(1L, "범죄도시", Genre.ACTION)),
                ScheduleInfo.Get.of(3L, MovieInfo.Get.of(1L, "범죄도시", Genre.ACTION))
        );
    }
}