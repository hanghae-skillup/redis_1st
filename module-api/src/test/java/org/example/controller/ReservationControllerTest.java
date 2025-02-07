package org.example.controller;

import org.example.baseresponse.BaseResponse;
import org.example.dto.request.ReservationRequestDto;
import org.example.dto.request.ReservationSeatDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @DisplayName("예매 성공 테스트")
    @Nested
    class reserveMovie_Success {
        @Test
        @DisplayName("좌석 1개 예매할 때 예매에 성공한다.")
        void reserveMovie_Success_1seat() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    1L, 1L, List.of(new ReservationSeatDto("ROW_A", "COL_1"))
            );

            ResponseEntity<BaseResponse> response = restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(1000, Objects.requireNonNull(response.getBody()).getCode());
        }

        @Test
        @DisplayName("좌석 5개 예매할 때 예매에 성공한다.")
        void reserveMovie_Success_5seat() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    2L,
                    1L,
                    List.of(new ReservationSeatDto("ROW_B", "COL_1"),
                            new ReservationSeatDto("ROW_B", "COL_2"),
                            new ReservationSeatDto("ROW_B", "COL_3"),
                            new ReservationSeatDto("ROW_B", "COL_4"),
                            new ReservationSeatDto("ROW_B", "COL_5"))
            );

            ResponseEntity<BaseResponse> response = restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(1000, Objects.requireNonNull(response.getBody()).getCode());
        }
    }

    @DisplayName("입력이 올바르지 않을 때 예매 실패 테스트")
    @Nested
    class reserveMovie_Fail_Null {
        @Test
        @DisplayName("userId가 null일 을 예매 실패")
        void reserveMovie_Fail_Null_UserId() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    null, 2L, List.of(new ReservationSeatDto("ROW_A", "COL_1"))
            );

            ResponseEntity<BaseResponse> response = restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        @DisplayName("screenScheduleId가 null일 때 예매 실패")
        void reserveMovie_Fail_Null_ScreenScheduleId() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    3L, null, List.of(new ReservationSeatDto("ROW_A", "COL_1"))
            );

            ResponseEntity<BaseResponse> response = restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        @DisplayName("좌석이 null일 때 예매 실패")
        void reserveMovie_Fail_Null_Seats() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    3L, 3L, null
            );

            ResponseEntity<BaseResponse> response = restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }
    }

    @DisplayName("유효하지 않은 좌석 번호일 때 예매 실패 테스트")
    @Nested
    class reserveMovie_Fail_Seat {
        @Test
        @DisplayName("유효하지 않은 좌석 번호일 때 예매 실패")
        void reserveMovie_Fail_InvalidSeat() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    4L, 4L, List.of(new ReservationSeatDto("A", "1"))
            );

            ResponseEntity<BaseResponse> response = restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        @DisplayName("유효하지 않은 열 번호로 예매 실패")
        void reserveMovie_Fail_InvalidCol() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    4L, 4L, List.of(new ReservationSeatDto("ROW_A", "COL_6"))
            );

            ResponseEntity<BaseResponse> response = restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        @DisplayName("유효하지 않은 행 번호로 예매 실패")
        void reserveMovie_Fail_InvalidRow() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    4L, 4L, List.of(new ReservationSeatDto("ROW_F", "COL_1"))
            );

            ResponseEntity<BaseResponse> response = restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }
    }

    @DisplayName("예매하려는 좌석이 올바르지 않을 때 예매 실패 테스트")
    @Nested
    class reserveMovie_Fail_SeatCount {
        @Test
        @DisplayName("예매하려는 좌석 개수가 0개 때 예매 실패")
        void reserveMovie_Fail_EmptySeats() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    5L,
                    5L,
                    List.of()
            );

            ResponseEntity<BaseResponse> response = restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        @DisplayName("예매 가능한 좌석 개수를 초과했을 때 예매 실패")
        void reserveMovie_Fail_ExceedSeatCount() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    5L,
                    5L,
                    List.of(new ReservationSeatDto("ROW_A", "COL_1"),
                            new ReservationSeatDto("ROW_A", "COL_2"),
                            new ReservationSeatDto("ROW_A", "COL_3"),
                            new ReservationSeatDto("ROW_A", "COL_4"),
                            new ReservationSeatDto("ROW_A", "COL_5"),
                            new ReservationSeatDto("ROW_A", "COL_6"))
            );

            ResponseEntity<BaseResponse> response = restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        @DisplayName("좌석이 같은 행이 아니면 예매 실패")
        void reserveMovie_Fail_SameRow() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    6L,
                    6L,
                    List.of(new ReservationSeatDto("ROW_A", "COL_1"),
                            new ReservationSeatDto("ROW_B", "COL_2"),
                            new ReservationSeatDto("ROW_C", "COL_3"))
            );

            ResponseEntity<BaseResponse> response = restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        @DisplayName("좌석이 연속된 열이 아니면 예매 실패")
        void reserveMovie_Fail_ContinuousCol() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    6L,
                    6L,
                    List.of(new ReservationSeatDto("ROW_A", "COL_1"),
                            new ReservationSeatDto("ROW_A", "COL_3"),
                            new ReservationSeatDto("ROW_A", "COL_4"))
            );

            ResponseEntity<BaseResponse> response = restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }
    }

    @DisplayName("같은 사용자 좌석 예매 실패 테스트")
    @Nested
    class reserveMovie_Fail_SameUser {
        @Test
        @DisplayName("같은 사용자가 5자리 이상 예매 시도할 경우 예매 실패")
        void reserveMovie_Fail_SameUser_ExceedSeatCount() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    7L,
                    7L,
                    List.of(new ReservationSeatDto("ROW_A", "COL_1"),
                            new ReservationSeatDto("ROW_A", "COL_2"),
                            new ReservationSeatDto("ROW_A", "COL_3"))
            );

            ReservationRequestDto requestDto2 = new ReservationRequestDto(
                    7L,
                    7L,
                    List.of(new ReservationSeatDto("ROW_A", "COL_4"),
                            new ReservationSeatDto("ROW_A", "COL_5"),
                            new ReservationSeatDto("ROW_B", "COL_1"))
            );

            restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            ResponseEntity<BaseResponse> response2 = restTemplate.postForEntity(
                    url, requestDto2, BaseResponse.class
            );

            assertEquals(HttpStatus.BAD_REQUEST, response2.getStatusCode());
        }

        @Test
        @DisplayName("같은 사용자가 다른 행의 좌석을 예매 시도할 경우 예매 실패")
        void reserveMovie_Fail_SameUser_IsNotSameRow() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    8L,
                    8L,
                    List.of(new ReservationSeatDto("ROW_A", "COL_1"),
                            new ReservationSeatDto("ROW_A", "COL_2"))
            );

            ReservationRequestDto requestDto2 = new ReservationRequestDto(
                    8L,
                    8L,
                    List.of(new ReservationSeatDto("ROW_B", "COL_1"),
                            new ReservationSeatDto("ROW_B", "COL_2"))
            );

            restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            ResponseEntity<BaseResponse> response2 = restTemplate.postForEntity(
                    url, requestDto2, BaseResponse.class
            );

            assertEquals(HttpStatus.BAD_REQUEST, response2.getStatusCode());
        }

        @Test
        @DisplayName("같은 사용자가 연속되지 않는 열의 좌석을 예매 시도할 경우 예매 실패")
        void reserveMovie_Fail_SameUser_IsNotContinuousCol() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    9L,
                    9L,
                    List.of(new ReservationSeatDto("ROW_A", "COL_1"),
                            new ReservationSeatDto("ROW_A", "COL_2"))
            );

            ReservationRequestDto requestDto2 = new ReservationRequestDto(
                    9L,
                    9L,
                    List.of(new ReservationSeatDto("ROW_A", "COL_4"),
                            new ReservationSeatDto("ROW_A", "COL_5"))
            );

            restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            ResponseEntity<BaseResponse> response2 = restTemplate.postForEntity(
                    url, requestDto2, BaseResponse.class
            );

            assertEquals(HttpStatus.BAD_REQUEST, response2.getStatusCode());
        }

        @Test
        @DisplayName("같은 사용자가 이미 예매한 좌석을 예매 시도할 경우 예매 실패")
        void reserveMovie_Fail_SameUser_AlreadyReserved() {
            String url = "http://localhost:" + port + "/reservation";

            ReservationRequestDto requestDto = new ReservationRequestDto(
                    10L,
                    10L,
                    List.of(new ReservationSeatDto("ROW_A", "COL_1"),
                            new ReservationSeatDto("ROW_A", "COL_2"))
            );

            ReservationRequestDto requestDto2 = new ReservationRequestDto(
                    10L,
                    10L,
                    List.of(new ReservationSeatDto("ROW_A", "COL_1"),
                            new ReservationSeatDto("ROW_A", "COL_2"))
            );

            restTemplate.postForEntity(
                    url, requestDto, BaseResponse.class
            );

            ResponseEntity<BaseResponse> response2 = restTemplate.postForEntity(
                    url, requestDto2, BaseResponse.class
            );

            assertEquals(HttpStatus.BAD_REQUEST, response2.getStatusCode());
        }
    }
}