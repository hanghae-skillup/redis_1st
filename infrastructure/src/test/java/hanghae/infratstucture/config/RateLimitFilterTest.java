package hanghae.infratstucture.config;

import hanghae.infrastructure.adapter.RateLimitFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RateLimitFilterTest {

    private RedissonClient redissonClient;
    private RateLimitFilter rateLimitFilter;

    @BeforeEach
    public void setUp() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        redissonClient = Redisson.create(config);
        redissonClient.getKeys().flushall();

        rateLimitFilter = new RateLimitFilter(redissonClient);
    }

    @Test
    public void testRateLimit() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("127.0.0.1");

        FilterChain filterChain = (req, res) -> {
            ((MockHttpServletResponse) res).setStatus(200);
        };

        MockHttpServletResponse response;

        // 1 ~ 50번 요청
        for (int i = 0; i < 50; i++) {
            response = new MockHttpServletResponse();
            rateLimitFilter.doFilter(request, response, filterChain);
            assertEquals(200, response.getStatus(), "Request " + (i + 1));
        }

        // 51번 째 요청
        response = new MockHttpServletResponse();
        rateLimitFilter.doFilter(request, response, filterChain);
        assertEquals(429, response.getStatus(), "51st request should be blocked");
    }
}
