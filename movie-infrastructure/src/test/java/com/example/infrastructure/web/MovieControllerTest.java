package com.example.infrastructure.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.application.dto.request.MovieRequestDto;
import com.example.application.dto.request.ScreeningRequestDto;
import com.example.domain.model.valueObject.ContentRating;
import com.example.domain.model.valueObject.Genre;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@DisplayName("[통합 테스트] 조회 컨트롤러")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @DisplayName("제목으로 영화 검색 시 해당 키워드를 포함한 영화 목록 및 200 응답을 반환한다.")
    @Test
    void givenTitleKeyword_whenSearchMovie_thenReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/movies")
                        .param("title", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").isNotEmpty());
    }

    @DisplayName("장르로 영화 검색 시 해당 장르의 영화 목록 및 200 응답을 반환한다.")
    @Test
    void givenGenre_whenSearchMovies_thenReturn200WithMovies() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/v1/movies")
                        .param("genre", "ACTION")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").isNotEmpty())
                .andExpect(jsonPath("$[0].genre").value("ACTION")); // 첫 번째 결과의 장르 확인
    }

    @DisplayName("동일 IP 요청이 Rate Limit 초과 시 429 응답을 반환하고, 타 IP는 요청이 허용된다.")
    @Test
    void givenRateLimitExceeded_whenGetMovies_thenReturn429() throws Exception {
        String firstIp = "192.223.1.1";
        String secondIp = "192.223.1.2";

        // Given: 첫 번째 IP에서 50번 조회 요청
        for (int i = 0; i < 50; i++) {
            mockMvc.perform(get("/api/v1/movies")
                            .param("title", "Movie_20")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("X-Forwarded-For", firstIp))
                    .andExpect(status().isOk());
        }

        // When: 첫 번째 IP에서 51번째 요청 시 Rate Limit 초과로 차단
        mockMvc.perform(get("/api/v1/movies")
                        .param("title", "Movie_20")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Forwarded-For", firstIp))
                .andExpect(status().isTooManyRequests());

        // Then: 다른 IP에서 요청하면 정상적으로 허용되어야 함
        mockMvc.perform(get("/api/v1/movies")
                        .param("title", "Movie_20")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Forwarded-For", secondIp))
                .andExpect(status().isOk());
    }

    @DisplayName("정상적인 영화 등록 요청 시 201 응답을 반환한다.")
    @Test
    void givenValidRequest_whenCreateMovie_thenReturn201() throws Exception {
        // Given
        MovieRequestDto validDto = new MovieRequestDto(
                "New Movie",
                ContentRating.ADULT,
                LocalDate.of(2024, 6, 15),
                "https://example.com/thumbnail.jpg",
                150,
                Genre.ACTION
        );

        // When & Then
        mockMvc.perform(post("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validDto)))
                .andExpect(status().isCreated());
    }

    @DisplayName("잘못된 영화 등록 요청 시 400 응답을 반환한다.")
    @Test
    void givenInvalidRequest_whenCreateMovie_thenReturn400() throws Exception {
        // Given: 제목이 비어 있음
        MovieRequestDto invalidDto = new MovieRequestDto(
                "", // 빈 제목 (유효하지 않음)
                ContentRating.ADULT,
                LocalDate.of(2024, 6, 15),
                "https://example.com/thumbnail.jpg",
                150,
                Genre.ACTION
        );

        // When & Then
        mockMvc.perform(post("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("영화 상영 시간 등록 시 201 응답을 반환한다.")
    @Test
    void givenValidScreeningRequest_whenCreateScreening_thenReturn201() throws Exception {
        // Given
        ScreeningRequestDto screeningRequestDto = new ScreeningRequestDto(1L, LocalTime.of(12, 0, 0));

        // When & Then
        mockMvc.perform(post("/api/v1/movies/1/screenings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(screeningRequestDto)))
                .andExpect(status().isCreated());
    }

    @DisplayName("잘못된 영화 상영 시간 등록 요청 시 400 응답을 반환한다.")
    @Test
    void givenInvalidScreeningRequest_whenCreateScreening_thenReturn400() throws Exception {
        // Given: 상영관 정보가 유효하지 않음
        ScreeningRequestDto screeningRequestDto = new ScreeningRequestDto(0L, LocalTime.of(12, 0, 0));

        // When & Then
        mockMvc.perform(post("/api/v1/movies/1/screenings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(screeningRequestDto)))
                .andExpect(status().isBadRequest());
    }

}
