package hellojpa.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import hellojpa.dto.RateLimitResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RateLimitInterceptorTest {

    private RateLimitInterceptor interceptor;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private PrintWriter responseWriter;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final StringWriter stringWriter = new StringWriter();

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        interceptor = new RateLimitInterceptor();

        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");
    }

    @Test
    void shouldAllowRequestWhenUnderLimit() throws Exception {
        // Given
        // Default setup is sufficient

        // When
        boolean result = interceptor.preHandle(request, response, null);

        // Then
        assertTrue(result);
        verify(response, never()).setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
    }

    @Test
    void shouldBlockRequestWhenOverMinuteLimit() throws Exception {
        // Given
        String testIp = "127.0.0.1";
        when(request.getRemoteAddr()).thenReturn(testIp);

        // When
        // Simulate 51 requests (over the 50 per minute limit)
        boolean lastResult = true;
        for (int i = 0; i < 51; i++) {
            lastResult = interceptor.preHandle(request, response, null);
            if (!lastResult) break;
        }

        // Then
        assertFalse(lastResult);
        verify(response, atLeastOnce()).setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        verify(response, atLeastOnce()).setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void shouldBlockIpAfterExceedingLimit() throws Exception {
        // Given
        String testIp = "127.0.0.1";
        when(request.getRemoteAddr()).thenReturn(testIp);

        // When
        // First exceed the limit
        for (int i = 0; i < 51; i++) {
            interceptor.preHandle(request, response, null);
        }

        // Then try one more request
        boolean result = interceptor.preHandle(request, response, null);

        // Then
        assertFalse(result);
        verify(response, atLeastOnce()).setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
    }

    @Test
    void shouldAllowDifferentIpWhenOneIpIsBlocked() throws Exception {
        // Given
        String blockedIp = "127.0.0.1";
        String allowedIp = "127.0.0.2";

        // Block first IP
        when(request.getRemoteAddr()).thenReturn(blockedIp);
        for (int i = 0; i < 51; i++) {
            interceptor.preHandle(request, response, null);
        }

        // When
        // Try request with different IP
        when(request.getRemoteAddr()).thenReturn(allowedIp);
        boolean result = interceptor.preHandle(request, response, null);

        // Then
        assertTrue(result);
    }

    @Test
    void shouldReturnCorrectResponseFormat() throws Exception {
        // Given
        String testIp = "127.0.0.1";
        when(request.getRemoteAddr()).thenReturn(testIp);

        // When
        // Exceed rate limit
        for (int i = 0; i < 51; i++) {
            interceptor.preHandle(request, response, null);
        }

        // Then
        String responseContent = stringWriter.toString();
        RateLimitResponseDto<?> responseDto = objectMapper.readValue(responseContent, RateLimitResponseDto.class);

        assertEquals(429, responseDto.getStatus());
        assertEquals("RATE_LIMIT_EXCEEDED", responseDto.getCode());
        assertEquals("요청 제한 횟수를 초과했습니다.", responseDto.getMessage());
        assertNull(responseDto.getData());
    }

    @Test
    void shouldRespectRateLimiterThrottling() throws Exception {
        // Given
        String testIp = "127.0.0.1";
        when(request.getRemoteAddr()).thenReturn(testIp);

        // When
        // Try to make many requests in quick succession
        int successfulRequests = 0;
        int totalRequests = 10;

        for (int i = 0; i < totalRequests; i++) {
            if (interceptor.preHandle(request, response, null)) {
                successfulRequests++;
            }
            // No sleep between requests to test rate limiting
        }

        // Then
        // Due to rate limiting (50 requests per minute), not all requests should succeed
        assertTrue(successfulRequests < totalRequests);
    }
}