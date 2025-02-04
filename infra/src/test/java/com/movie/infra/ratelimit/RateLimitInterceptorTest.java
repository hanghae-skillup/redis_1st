package com.movie.infra.ratelimit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.infra.common.response.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RateLimitInterceptorTest {

    @Mock
    private RateLimitService rateLimitService;

    private RateLimitInterceptor interceptor;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        interceptor = new RateLimitInterceptor(rateLimitService, objectMapper);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    @DisplayName("RateLimit 통과 시 true 반환")
    void shouldReturnTrueWhenRateLimitPasses() throws Exception {
        // given
        when(rateLimitService.tryAcquire(anyString())).thenReturn(true);

        // when
        boolean result = interceptor.preHandle(request, response, null);

        // then
        assertThat(result).isTrue();
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("RateLimit 초과 시 429 에러 반환")
    void shouldReturn429WhenRateLimitExceeded() throws Exception {
        // given
        when(rateLimitService.tryAcquire(anyString())).thenReturn(false);

        // when
        boolean result = interceptor.preHandle(request, response, null);

        // then
        assertThat(result).isFalse();
        assertThat(response.getStatus()).isEqualTo(429);
        assertThat(response.getContentType()).isEqualTo("application/json");
        
        String responseBody = response.getContentAsString();
        assertThat(responseBody).contains("\"code\":429");
        assertThat(responseBody).contains(ErrorCode.RATE_LIMIT_EXCEEDED.getMessage());
    }
} 