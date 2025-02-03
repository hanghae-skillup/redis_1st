package com.example.config.ratelimit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GuavaRateLimitTest {

    private final RateLimit rateLimit = new GuavaRateLimit();

    @Test
    @DisplayName("초당 요청 제한 테스트: 연속 요청 시 per-second RateLimiter가 제한하는지 확인")
    void test1() {
        String ip = "192.168.1.101";  // 테스트용 IP

        int allowedCount = 0;
        int totalRequests = 10;

        for (int i = 0; i < totalRequests; i++) {
            if (rateLimit.isAllowed(ip)) {
                allowedCount++;
            }
        }

        assertThat(allowedCount < totalRequests).isTrue();
        assertThat(allowedCount <= 3).isTrue();
    }

    @Test
    @DisplayName("1분 내 50회 초과 요청 시 IP 차단 테스트: 51번째 요청부터 차단된다")
    void test2() throws InterruptedException {
        String ip = "192.168.1.101";

        for (int i = 0; i < 50; i++) {
            assertThat(rateLimit.isAllowed(ip)).isTrue();
            Thread.sleep(1000);
        }

        boolean allowed = rateLimit.isAllowed(ip);
        assertThat(allowed).isFalse();

        assertThat(rateLimit.isAllowed(ip)).isFalse();
    }
}