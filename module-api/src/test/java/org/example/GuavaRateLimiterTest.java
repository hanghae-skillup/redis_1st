package org.example;

import com.google.common.util.concurrent.RateLimiter;
import org.example.service.movie.FindMovieService;
import org.example.service.movie.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuavaRateLimiterTest {
    @MockBean
    private FindMovieService findMovieService;

    private MovieService movieService;

    private RateLimiter rateLimiter;

    @BeforeEach
    void setUp() throws InterruptedException {
        MockitoAnnotations.openMocks(this);
        rateLimiter = RateLimiter.create(2.0); // 초당 2개의 요청을 허용하는 RateLimiter
        movieService = new MovieService(findMovieService, rateLimiter);
        Thread.sleep(500);
    }

    @Test
    void testRateLimiter_AllowsOnlyTwoRequestsPerSecond() {
        assertTrue(movieService.rateLimiter.tryAcquire()); // 첫 번째 요청 허용
        assertTrue(movieService.rateLimiter.tryAcquire()); // 두 번째 요청 허용
        assertFalse(movieService.rateLimiter.tryAcquire()); // 세 번째 요청 거부
    }

    @Test
    void testRateLimiter_AllowsRequestAfterDelay() throws InterruptedException {
        assertTrue(movieService.rateLimiter.tryAcquire()); // 첫 번째 요청 허용
        assertTrue(movieService.rateLimiter.tryAcquire()); // 두 번째 요청 허용
        assertFalse(movieService.rateLimiter.tryAcquire()); // 세 번째 요청 거부

        Thread.sleep(1000); // 1초 후 토큰이 다시 충전됨

        assertTrue(movieService.rateLimiter.tryAcquire()); // 다시 요청 가능
    }
}
