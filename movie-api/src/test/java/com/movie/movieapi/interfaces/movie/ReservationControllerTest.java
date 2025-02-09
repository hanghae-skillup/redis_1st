package com.movie.movieapi.interfaces.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.application.movie.facade.ReservationFacade;
import com.movie.common.response.Response;
import com.movie.domain.movie.dto.command.ReservationCommand;
import com.movie.movieapi.MovieApiApplication;
import com.movie.movieapi.interfaces.movie.dto.ReservationDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MovieApiApplication.class)
@AutoConfigureMockMvc
class ReservationControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    ReservationFacade reservationFacade;

    @DisplayName("스케줄과 여러 좌석을 선택하여, 예약을 등록한다.")
    @Test
    void given_when_then() throws Exception {
        Long scheduleId = 1L;
        List<Long> seatIds = List.of(1L, 2L, 3L);
        ReservationDto.Reserve reserve = ReservationDto.Reserve.of(scheduleId, seatIds);
        String token = "734488355d85";
        ReservationCommand.GetReserveData command = reserve.toCommand(token);
        doNothing().when(reservationFacade).makeReservationsByFunctionalLock(command);

        mockMvc.perform(post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(reserve))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().bytes(objectMapper.writeValueAsBytes(Response.success())));

    }

}