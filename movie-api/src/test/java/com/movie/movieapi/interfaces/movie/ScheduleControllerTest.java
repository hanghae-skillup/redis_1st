package com.movie.movieapi.interfaces.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.common.enums.Genre;
import com.movie.domain.movie.ScheduleService;
import com.movie.domain.movie.domain.Movie;
import com.movie.domain.movie.domain.TimeTable;
import com.movie.domain.movie.dto.command.ScheduleCommand;
import com.movie.common.response.Response;
import com.movie.domain.movie.dto.info.*;
import com.movie.movieapi.MovieApiApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MovieApiApplication.class)
@AutoConfigureMockMvc
class ScheduleControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    ScheduleService scheduleService;

    @DisplayName("영화관 id를 이용하여, 검색된 스케줄 목록을 반환한다.")
    @Test
    void givenTheaterId_whenRequestingSchedules_thenReturnsSchedules() throws Exception {
        // given
        Long theaterId = 1L;
        given(scheduleService.getSchedules(theaterId)).willReturn(List.of());

        // when
        mockMvc.perform(get("/api/schedules")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("theaterId", String.valueOf(theaterId)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().bytes(objectMapper.writeValueAsBytes(Response.success(List.of()))));

        // then
        then(scheduleService).should().getSchedules(theaterId);
    }

    @DisplayName("영화 제목과 장르로 스케줄 목록을 요청하면, 검색된 스케줄 목록을 반환한다.")
    @Test
    void givenScheduleTitleAndGenre_whenRequestingSchedules_thenReturnsSchedules() throws Exception {
        // given
        ScheduleCommand.Search search = ScheduleCommand.Search.of("1", null);
        given(scheduleService.getSchedules(search)).willReturn(List.of());

        // when
        mockMvc.perform(post("/api/schedules")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(search)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().bytes(objectMapper.writeValueAsBytes(Response.success(List.of()))));

        // then
        then(scheduleService).should().getSchedules(search);
    }

    @DisplayName("영화 제목과 장르로 스케줄 목록을 요청하면, 인메모리 캐시에서 검색된 스케줄 목록을 반환한다.")
    @Test
    void givenScheduleTitleAndGenre_whenSearchingSchedules_thenReturnsSchedulesFromCache() throws Exception {
        // given
        ScheduleCommand.Search search = ScheduleCommand.Search.of("범죄도시", Genre.ACTION);
        List<ScheduleInfo.Get> schedules = List.of(
                ScheduleInfo.Get.of(1L,
                        TheaterInfo.Get.of(1L, "영화관1"),
                        ScreenInfo.Get.of(1L, 1L, "상영관1"),
                        MovieInfo.Get.of(1L, "범죄도시", Genre.ACTION),
                        List.of(TimeTableInfo.Get.of(LocalDateTime.now(), LocalDateTime.now()))
                )
        );
        given(scheduleService.getSchedulesAsCached(search)).willReturn(schedules);

        // when
        mockMvc.perform(post("/api/schedules/cached")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(search)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().bytes(objectMapper.writeValueAsBytes(Response.success(schedules))));

        // then
        then(scheduleService).should().getSchedulesAsCached(search);
    }

    @DisplayName("영화 제목과 장르로 스케줄 목록을 요청하면, Redis 캐시에서 검색된 스케줄 목록을 반환한다.")
    @Test
    void givenScheduleTitleAndGenre_whenSearchingSchedules_thenReturnsSchedulesFromRedis() throws Exception {
        // given
        ScheduleCommand.Search search = ScheduleCommand.Search.of("범죄도시", Genre.ACTION);
        List<ScheduleInfo.Get> schedules = List.of(
                ScheduleInfo.Get.of(1L,
                        TheaterInfo.Get.of(1L, "영화관1"),
                        ScreenInfo.Get.of(1L, 1L, "상영관1"),
                        MovieInfo.Get.of(1L, "범죄도시", Genre.ACTION),
                        List.of(TimeTableInfo.Get.of(LocalDateTime.now(), LocalDateTime.now()))
                )
        );
        given(scheduleService.getSchedulesByRedis(search)).willReturn(schedules);

        // when
        mockMvc.perform(post("/api/schedules/redis")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(search)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().bytes(objectMapper.writeValueAsBytes(Response.success(schedules))));

        // then
        then(scheduleService).should().getSchedulesByRedis(search);
    }

}