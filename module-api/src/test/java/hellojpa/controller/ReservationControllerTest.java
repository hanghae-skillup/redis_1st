package hellojpa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hellojpa.dto.RateLimitResponseDto;
import hellojpa.dto.ReservationRequestDto;
import hellojpa.service.ReservationRateLimitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Transactional
class ReservationControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReservationRateLimitService reservationRateLimitService;

    private WebTestClient webTestClient;
    private final int THREAD_COUNT = 10; // 동시에 요청할 스레드 개수
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    @BeforeEach
    void setUp() {
        this.webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
        //reservationRateLimitService.resetAllLimits();
    }

    @Test
    void 예약_정상처리() {
        // Given
        ReservationRequestDto requestDto1 = new ReservationRequestDto(1L, 1L, List.of(14L, 15L));

        // When
        var response = webTestClient.post()
                .uri("/reservation/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto1)
                .exchange();

        // Then
        response.expectStatus().isOk()
                .expectBody(RateLimitResponseDto.class)
                .value(res -> {
                    assertThat(res.getStatus()).isEqualTo(200);
                    assertThat(res.getCode()).isEqualTo("success");
                    assertThat(res.getMessage()).isEqualTo("요청에 성공했습니다.");
                });
    }

    @Test
    void 예약_예외처리() {
        // Given
        ReservationRequestDto requestDto1 = new ReservationRequestDto(2L, 1L, List.of(1L, 3L));

        // When
        var response = webTestClient.post()
                .uri("/reservation/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto1)
                .exchange();

        // Then
        response.expectStatus().isBadRequest()
                .expectBody(RateLimitResponseDto.class)
                .value(res -> {
                    assertThat(res.getStatus()).isEqualTo(400);
                });
    }

    @Test
    void RateLimit_초과시_예약_차단() {
        // Given
        ReservationRequestDto requestDto1 = new ReservationRequestDto(3L, 1L, List.of(1L, 2L));
        ReservationRequestDto requestDto2 = new ReservationRequestDto(3L, 1L, List.of(3L, 4L, 5L));


        webTestClient.post()
                .uri("/reservation/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto1)
                .exchange();

        // When
        var response = webTestClient.post()
                .uri("/reservation/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto2)
                .exchange();

        // Then
        response.expectStatus().isEqualTo(HttpStatus.TOO_MANY_REQUESTS)
                .expectBody(RateLimitResponseDto.class)
                .value(res -> {
                    assertThat(res.getStatus()).isEqualTo(429);
                    assertThat(res.getCode()).isEqualTo("RATE_LIMIT_EXCEEDED");
                });
    }

    @Test
    void 동시_예약_테스트() throws InterruptedException, ExecutionException, JsonProcessingException {
        // Given - 동일한 좌석을 동시에 예약하려는 요청들 생성
        Long userId = 4L;
        Long screeningId = 1L;
        List<Long> seatIds = List.of(25L); // 같은 좌석을 여러 요청이 시도

        List<Callable<WebTestClient.ResponseSpec>> tasks = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            tasks.add(() -> webTestClient.post()
                    .uri("/reservation/movie")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(new ReservationRequestDto(userId, screeningId, seatIds))
                    .exchange());
        }

        // When - 동시에 실행
        List<Future<WebTestClient.ResponseSpec>> futures = executorService.invokeAll(tasks);

        // Then - 결과 검증
        int successCount = 0;
        int failCount = 0;

        for (Future<WebTestClient.ResponseSpec> future : futures) {
            WebTestClient.ResponseSpec response = future.get();

            // 서버에서 실제 응답된 Content-Type과 Body를 출력하여 확인
            String responseBody = response.expectBody(String.class).returnResult().getResponseBody();
            System.out.println("응답 Body: " + responseBody);

            // JSON 파싱하여 DTO로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            RateLimitResponseDto rateLimitResponse = objectMapper.readValue(responseBody, RateLimitResponseDto.class);

            HttpStatus status = HttpStatus.valueOf(rateLimitResponse.getStatus());

            if (status.equals(HttpStatus.OK)) {
                successCount++;
            } else {
                failCount++;
            }
        }

        System.out.println("성공한 예약 개수: " + successCount);
        System.out.println("실패한 예약 개수: " + failCount);

        // 하나 이상의 요청이 성공하고, 일부 요청은 실패해야 함 (좌석 중복 방지 로직이 동작해야 함)
        assertThat(successCount).isGreaterThan(0);
        assertThat(failCount).isGreaterThan(0);
    }
}
