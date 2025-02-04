package com.movie.movieapi.interfaces.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.domain.movie.ScheduleService;
import com.movie.domain.movie.dto.command.ScheduleCommand;
import com.movie.common.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ScheduleControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    ScheduleService scheduleService;

    @DisplayName("영화 제목과 장르로 스케줄 목록을 요청하면, 검색된 스케줄 목록을 반환한다.")
    @Test
    void givenScheduleTitleAndGenre_whenRequestingSchedules_thenReturnsSchedulesBySearch() throws Exception {
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

}