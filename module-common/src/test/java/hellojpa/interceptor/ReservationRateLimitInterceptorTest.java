package hellojpa.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import hellojpa.dto.RateLimitResponseDto;
import hellojpa.dto.ReservationRequestDto;
import hellojpa.service.ReservationRateLimitService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReservationRateLimitInterceptorTest {

    @Mock
    private ReservationRateLimitService reservationRateLimitService;

    @InjectMocks
    private ReservationRateLimitInterceptor reservationRateLimitInterceptor;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HandlerMethod handlerMethod;

    private Long userId = 1L;
    private Long screeningId = 1L;
    private ObjectMapper objectMapper;
    private StringWriter responseWriter;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();

        // 응답 Writer 설정
        responseWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(responseWriter);
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    public void testRateLimitWithRetryWithin5Minutes() throws Exception {
        // 1. 첫 번째 예약 요청 → 허용
        doReturn(true).when(reservationRateLimitService).isAllowed(userId, screeningId);

        // 요청 JSON 생성
        ReservationRequestDto requestDto = new ReservationRequestDto(userId, screeningId, List.of(1L, 2L));
        String body = objectMapper.writeValueAsString(requestDto);

        // 첫 번째 요청
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(body)));
        boolean firstResult = reservationRateLimitInterceptor.preHandle(request, response, handlerMethod);  // null 대신 handlerMethod 사용
        assertTrue(firstResult, "첫 번째 요청이 허용되지 않았습니다.");

        // 2. 두 번째 예약 요청 (5분 내 재시도) → 차단
        doReturn(false).when(reservationRateLimitService).isAllowed(userId, screeningId); // 두 번째 요청은 차단

        // 두 번째 요청
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(body)));
        boolean secondResult = reservationRateLimitInterceptor.preHandle(request, response, handlerMethod);  // null 대신 handlerMethod 사용
        assertFalse(secondResult, "두 번째 요청이 차단되지 않았습니다.");

        // 응답이 429 상태 코드인지 확인
        verify(response).setStatus(HttpStatus.TOO_MANY_REQUESTS.value());

        // 응답 본문을 JSON으로 변환하여 검증
        responseWriter.flush(); // PrintWriter 내용을 보장
        String jsonResponse = responseWriter.toString();
        System.out.println("Response Body: " + jsonResponse); // 디버깅 메시지
        RateLimitResponseDto<?> actualResponse = objectMapper.readValue(jsonResponse, RateLimitResponseDto.class);

        assertEquals(429, actualResponse.getStatus(), "상태 코드가 예상과 다릅니다.");
        assertEquals("RATE_LIMIT_EXCEEDED", actualResponse.getCode(), "에러 코드가 예상과 다릅니다.");
        assertEquals("5분 후 예약을 다시 시도해주세요.", actualResponse.getMessage(), "에러 메시지가 예상과 다릅니다.");
        assertNull(actualResponse.getData(), "응답 데이터는 null이어야 합니다.");

        // isAllowed() 호출 횟수 검증
        verify(reservationRateLimitService, times(2)).isAllowed(userId, screeningId);
    }

}