package hellojpa.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hellojpa.dto.RateLimitResponseDto;
import hellojpa.service.ReservationRateLimitService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReservationRateLimitInterceptor implements HandlerInterceptor {

    private final ReservationRateLimitService reservationRateLimitService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true; // 컨트롤러의 요청이 아닐 경우 통과
        }

        // 요청에서 userId, screeningId 추출
        String body = request.getReader().lines().collect(Collectors.joining());
        JsonNode jsonNode = objectMapper.readTree(body);

        Long userId = jsonNode.get("userId").asLong();
        Long screeningId = jsonNode.get("screeningId").asLong();

        // RateLimit 검사
        boolean allowed = reservationRateLimitService.isAllowed(userId, screeningId);
        if (!allowed) {
            sendRateLimitResponse(response);
            return false; // 요청 차단
        }

        return true; // 요청 허용
    }

    private void sendRateLimitResponse(HttpServletResponse response) throws IOException {
        String rateLimitExceeded = objectMapper.writeValueAsString(new RateLimitResponseDto<>(
                429, "RATE_LIMIT_EXCEEDED", "5분 후 예약을 다시 시도해주세요.", null)
        );

        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(rateLimitExceeded);
    }
}
