package com.movie.movieapi.config.interceptor;

import com.movie.movieapi.config.WebConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(WebConfig.class)
class DataFetchRateLimitInterceptorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAllowRequestWhenWithinLimit() throws Exception {
        String ip = "192.168.0.1";

        // 각 요청에 대한 응답을 저장
        List<ResultActions> resultActionsList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            try{
                resultActionsList.add(mockMvc.perform(
                        get("/api/schedules")
                                .param("theaterId", "1")
                                .header("X-Forwarded-For", ip) // 임의로 IP를 헤더에 설정
                ));
//                Thread.sleep(1000);
            } catch (Exception e) {
                // 예외가 발생하면 계속해서 실패 메시지 출력 (예: rate limit 초과 시)
                System.out.println("Request " + (i + 1) + " failed due to: " + e.getMessage());
            }
        }

        // 각 요청에 대해 응답 상태를 검사
        for (ResultActions resultActions : resultActionsList) {
            resultActions
                    .andExpect(status().isOk());  // 제한 내에서의 요청은 정상적으로 응답
        }

        // 51번째 요청은 rate limit에 의해 실패해야 함
        mockMvc.perform(
                        get("/api/schedules")
                                .param("theaterId", "1")
                                .header("X-Forwarded-For", ip)
                )
                .andDo(print())
                .andExpect(status().isTooManyRequests());       // 요청이 초과되었으므로 429 Too Many Requests 응답
    }

    @Test
    void shouldHandleMultipleUsersWithDifferentIps() throws Exception {
        int totalRequests = 500; // 총 요청 횟수

        ExecutorService executor = Executors.newFixedThreadPool(totalRequests);
        CountDownLatch latch = new CountDownLatch(totalRequests);

        // 5개의 서로 다른 IP 주소
        String[] ipAddresses = {
                "192.168.0.1",
                "192.168.0.2",
                "192.168.0.3",
                "192.168.0.4",
                "192.168.0.5"
        };

        // 총 100개의 요청을 보내고 각 요청마다 랜덤 IP 선택
        for (int i = 0; i < totalRequests; i++) {
            executor.submit(() -> {
                String ip = ipAddresses[(int) (Math.random() * ipAddresses.length)];  // 랜덤으로 IP 선택
                try {
                    mockMvc.perform(get("/api/schedules")
                                    .param("theaterId", "1")
                                    .header("X-Forwarded-For", ip))             // 임의로 IP를 헤더에 설정
                            .andDo(print())
                            .andExpect(status().isOk());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
    }
}