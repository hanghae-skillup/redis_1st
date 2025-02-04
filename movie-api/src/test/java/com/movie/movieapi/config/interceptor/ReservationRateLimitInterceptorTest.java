package com.movie.movieapi.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.movieapi.config.WebConfig;
import com.movie.movieapi.interfaces.movie.dto.ReservationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(WebConfig.class)
class ReservationRateLimitInterceptorTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void given_when_then() throws Exception {
        // given
        String ip = "192.168.0.1";
        Long scheduleId = 1L;
        List<Long> seatIds = List.of(1L, 2L, 3L);
        ReservationDto.Reserve reserve = ReservationDto.Reserve.of(scheduleId, seatIds);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Forwarded-For", ip);
        headers.add(HttpHeaders.AUTHORIZATION, "734488355d85");

        // when & then
        mockMvc.perform(
                        post("/api/reservations")
                                .content(objectMapper.writeValueAsBytes(reserve))
                                .headers(headers)
                )
                .andDo(print())
                .andExpect(status().isOk());

        Thread.sleep(1000);

        mockMvc.perform(
                        post("/api/reservations")
                                .content(objectMapper.writeValueAsBytes(reserve))
                                .headers(headers)
                )
                .andDo(print())
                .andExpect(status().isAccepted());

    }

}