package com.example.infrastructure.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.application.dto.request.ReservationRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@DisplayName("[통합 테스트] 예약 컨트롤러")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @DisplayName("정상적인 예약 요청 시 200 응답을 반환한다.")
    @Test
    void givenValidRequest_whenCreateReservation_thenReturn200() throws Exception {
        // Given
        ReservationRequestDto reservationRequestDto = new ReservationRequestDto(2L, 1L, List.of(1L, 2L, 3L));

        // When & Then
        mockMvc.perform(post("/api/v1/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservationRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberName").exists())
                .andExpect(jsonPath("$.movieTitle").exists())
                .andExpect(jsonPath("$.theaterName").exists())
                .andExpect(jsonPath("$.screeningStartTime").exists())
                .andExpect(jsonPath("$.reservedSeats").exists())
                .andExpect(jsonPath("$.reservedSeats").isArray());
    }


    @DisplayName("잘못된 요청 값으로 시도하면 400 응답을 반환한다.")
    @Test
    void givenInvalidRequest_whenCreateReservation_thenReturn400() throws Exception {
        // Given: 좌석이 입력되지 않음
        ReservationRequestDto reservationRequestDto = new ReservationRequestDto(1L, 3L, List.of());

        // When & Then
        mockMvc.perform(post("/api/v1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reservationRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Rate Limit 초과 시 429 응답을 반환한다.")
    @Test
    void givenRateLimitExceeded_whenCreateReservation_thenReturn429() throws Exception {
        // Given
        ReservationRequestDto firstRequest = new ReservationRequestDto(1L, 2L, List.of(4L, 5L));
        ReservationRequestDto secondRequest = new ReservationRequestDto(1L, 2L, List.of(6L, 7L));

        // When: 첫 번째 예약 요청은 성공
        mockMvc.perform(post("/api/v1/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstRequest)))
                .andExpect(status().isOk());

        // Then: 동일한 영화 및 시간대에 두 번째 요청은 Rate Limit 으로 실패
        mockMvc.perform(post("/api/v1/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(secondRequest)))
                .andExpect(status().isTooManyRequests());
    }

}