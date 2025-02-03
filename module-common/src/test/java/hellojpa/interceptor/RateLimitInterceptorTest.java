package hellojpa.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import hellojpa.dto.RateLimitResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class RateLimitInterceptorTest {

    @InjectMocks
    private RateLimitInterceptor rateLimitInterceptor;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HandlerMethod handlerMethod;

    private String ip = "192.168.1.1";
    private ObjectMapper objectMapper;
    private StringWriter responseWriter;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        //rateLimitInterceptor = new RateLimitInterceptor();
        objectMapper = new ObjectMapper();

        // 응답에 대한 Mock 설정
        responseWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(responseWriter);
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    public void testRateLimitInterceptor() throws Exception {

        when(request.getRemoteAddr()).thenReturn(ip);

        // 50번 요청 정상 처리
        for (int i = 1; i <= 50; i++) {
            boolean result = rateLimitInterceptor.preHandle(request, response, handlerMethod);
            assertTrue(result, i + "번째 요청 통과 실패");

            // 각 요청 간 간격을 두어야 RateLimiter가 제대로 동작함
            Thread.sleep(1000); // 1.2초 대기
        }

        // 51번째 요청은 차단되어야 함
        boolean finalResult = rateLimitInterceptor.preHandle(request, response, handlerMethod);
        assertFalse(finalResult, "51번째 요청 차단 실패");

        // 응답이 429 상태 코드인지 확인
        verify(response).setStatus(HttpStatus.TOO_MANY_REQUESTS.value());

        // 응답 본문을 JSON으로 변환하여 검증
        responseWriter.flush(); // PrintWriter 내용을 보장
        String jsonResponse = responseWriter.toString();
        System.out.println("Response Body: " + jsonResponse); // 디버깅 메시지
        RateLimitResponseDto<?> actualResponse = objectMapper.readValue(jsonResponse, RateLimitResponseDto.class);

        assertEquals(429, actualResponse.getStatus(), "상태 코드가 예상과 다릅니다.");
        assertEquals("RATE_LIMIT_EXCEEDED", actualResponse.getCode(), "에러 코드가 예상과 다릅니다.");
        assertEquals("요청 제한 횟수를 초과했습니다.", actualResponse.getMessage(), "에러 메시지가 예상과 다릅니다.");
        assertNull(actualResponse.getData(), "응답 데이터는 null이어야 합니다.");
    }
}