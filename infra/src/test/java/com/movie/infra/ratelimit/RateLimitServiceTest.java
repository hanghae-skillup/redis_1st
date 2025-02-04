package com.movie.infra.ratelimit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateLimitServiceTest {

    private RateLimitService rateLimitService;

    @BeforeEach
    void setUp() {
        rateLimitService = new RateLimitService();
    }

    @Test
    @DisplayName("IP별 요청 제한 - 정상 케이스")
    void shouldAllowRequestsWithinLimit() {
        String ip = "127.0.0.1";
        
        // 1분에 50회 요청 가능
        for (int i = 0; i < 50; i++) {
            assertThat(rateLimitService.tryAcquire(ip)).isTrue();
        }
    }

    @Test
    @DisplayName("IP별 요청 제한 초과 시 차단")
    void shouldBlockIpWhenExceedingLimit() {
        String ip = "127.0.0.1";
        
        // 51회 요청 시도 (제한: 50회)
        for (int i = 0; i < 50; i++) {
            rateLimitService.tryAcquire(ip);
        }
        
        // 51번째 요청은 차단되어야 함
        assertThat(rateLimitService.tryAcquire(ip)).isFalse();
        
        // 차단된 IP는 계속 차단 상태여야 함
        assertThat(rateLimitService.isBanned(ip)).isTrue();
    }

    @Test
    @DisplayName("서로 다른 IP는 독립적으로 제한되어야 함")
    void shouldLimitRequestsIndependentlyForDifferentIps() {
        String ip1 = "127.0.0.1";
        String ip2 = "127.0.0.2";
        
        // ip1 차단
        for (int i = 0; i < 51; i++) {
            rateLimitService.tryAcquire(ip1);
        }
        
        // ip1은 차단, ip2는 정상 요청 가능해야 함
        assertThat(rateLimitService.tryAcquire(ip1)).isFalse();
        assertThat(rateLimitService.tryAcquire(ip2)).isTrue();
    }

    @Test
    @DisplayName("차단 시간 경과 후 요청 가능 여부 확인")
    void shouldAllowRequestAfterBanExpires() throws InterruptedException {
        String ip = "127.0.0.1";
        
        // Ban 상태로 만들기
        for (int i = 0; i < 51; i++) {
            rateLimitService.tryAcquire(ip);
        }
        
        assertThat(rateLimitService.isBanned(ip)).isTrue();
        
        // Ban 해제 시간이 지난 것처럼 처리
        Thread.sleep(100); // 실제 테스트에서는 mock 사용 권장
        
        assertThat(rateLimitService.isBanned(ip)).isFalse();
        assertThat(rateLimitService.tryAcquire(ip)).isTrue();
    }
} 