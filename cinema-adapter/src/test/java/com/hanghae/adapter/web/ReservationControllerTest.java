package com.hanghae.adapter.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.adapter.TestDataFactory;
import com.hanghae.application.dto.request.MovieReservationRequestDto;
import com.hanghae.application.port.in.MovieReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles("test") // application-test.properties 사용
@AutoConfigureMockMvc
@Transactional
@Sql(scripts = "/sql/reservationTest.sql") // SQL 파일 실행
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MovieReservationService movieReservationService;

    private MovieReservationRequestDto requestDto;

    @BeforeEach
    void setUp() {
        requestDto = TestDataFactory.createMovieReservationRequestDto();
    }

    @Test
    @DisplayName("[통합] 정상적인 영화 예매 요청 테스트")
    void saveMovieReservationSuccess() throws Exception {
        // When & Then
        mockMvc.perform(post("/api/v1/movie-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("예매가 완료 되었습니다."));
    }

    @Test
    @DisplayName("[통합] 5분내 동일 일정 영화 예매 예외 발생 테스트")
    void saveMovieReservationTooManyRequests() throws Exception {
        // 1번 요청을 먼저 수행하여 Redis Rate Limit를 초과시키기
        mockMvc.perform(post("/api/v1/movie-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated());

        // 5분 내 동일한 요청을 보내면 예외 발생 확인
        mockMvc.perform(post("/api/v1/movie-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isTooManyRequests())
                .andExpect(jsonPath("$.message").value("동일 시간대 영화 예매는 5분 후 가능합니다."));
    }

    @Test
    @DisplayName("[통합] 좌석이 이미 예매된 경우 예외 발생 테스트")
    void saveMovieReservation_SeatAlreadyReserved() throws Exception {
        //테스트 sql에서 미리 넣어둔 예매데이터와 동일한 좌석 세팅
        requestDto = TestDataFactory.createMovieReservationRequestDto("E", 1);

        // 동일한 좌석을 다시 예매하면 예외 발생 확인
        mockMvc.perform(post("/api/v1/movie-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("선택한 좌석은 이미 예매되었습니다."));
    }

    @Test
    @DisplayName("[통합] 회원당 예매 좌석 개수 초과시 예외 발생 테스트")
    void saveMovieReservationSeatLimitExceeded() throws Exception {
        //테스트 sql에서 미리 넣어둔 예매데이터 + 추가로 5좌석 예매
        MovieReservationRequestDto invalidRequest = TestDataFactory.createMovieReservationRequestDto(5);

        // 좌석 5개 초과 예매 요청시 예외 발생 확인
        mockMvc.perform(post("/api/v1/movie-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("상영시간별 예매는 최대 5개까지 할 수 있습니다."));
    }
}