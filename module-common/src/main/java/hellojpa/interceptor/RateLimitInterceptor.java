package hellojpa.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import hellojpa.dto.RateLimitResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private static final int REQUEST_LIMIT = 50;
    private static final int BLOCK_HOURS = 1;
    private static final int TIME_MINUTE = 60;

    private final Map<String, RequestData> requestDataMap = new ConcurrentHashMap<>();
    private final Map<String, LocalDateTime> blockedIps = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientIp = request.getRemoteAddr();

        // IP가 차단 상태인지 확인
        if (isBlocked(clientIp)) {
            sendRateLimitResponse(response);
            return false;
        }

        // 요청 시간 기록 및 카운팅
        RequestData data = requestDataMap.computeIfAbsent(clientIp, k -> new RequestData());
        LocalDateTime now = LocalDateTime.now();
        data.addRequest(now);

        // 1분 내 요청 횟수 체크
        if (data.getRequestCountInLastMinute(now) > REQUEST_LIMIT) {
            blockedIps.put(clientIp, LocalDateTime.now().plusHours(BLOCK_HOURS));
            sendRateLimitResponse(response);
            return false;
        }

        return true;
    }

    private boolean isBlocked(String clientIp) {
        if (blockedIps.containsKey(clientIp)) {
            LocalDateTime blockUntil = blockedIps.get(clientIp);
            // 차단 시간이 지나면 차단 해제
            if (LocalDateTime.now().isAfter(blockUntil)) {
                blockedIps.remove(clientIp);
                return false;
            }
            return true;
        }
        return false;
    }

    private void sendRateLimitResponse(HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String rateLimitExceeded = objectMapper.writeValueAsString(new RateLimitResponseDto<>(
                429, "RATE_LIMIT_EXCEEDED", "요청 제한 횟수를 초과했습니다.", null)
        );

        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(rateLimitExceeded);
    }

    // 요청 횟수와 시간을 관리
    private static class RequestData {
        private final List<LocalDateTime> requests = new ArrayList<>();

        // 요청 시간 추가
        public void addRequest(LocalDateTime requestTime) {
            // 1분 이상 지난 요청들을 삭제
            requests.removeIf(time -> time.isBefore(requestTime.minusSeconds(TIME_MINUTE)));
            requests.add(requestTime);
        }

        // 1분 내 요청 횟수 계산
        public long getRequestCountInLastMinute(LocalDateTime now) {
            // 1분 전 요청들을 제외하고 남은 요청 수를 반환
            return requests.size();
        }
    }
}