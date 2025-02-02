package com.example.redis

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MovieControllerTest: BehaviorSpec() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    override fun extensions() = listOf(SpringExtension)

    init {
        this.given("Movies") {
            val ip = "192.168.1.1"
            val threadCount = 55
            val executor = Executors.newFixedThreadPool(threadCount)
            var allowedRequests = 0
            var rejectedRequests = 0

            `when`("영화 리스트 API 호출 과정에서 요청 횟수를 초과할 경우") {
                repeat(threadCount) {
                    executor.execute {
                        val response = mockMvc.get("/api/v1/movies") {
                            header("X-Forwarded-For", ip)
                        }.andReturn().response

                        synchronized(this) {
                            val status = response.status
                            if (status == HttpStatus.OK.value()) {
                                allowedRequests++
                            } else if (status == HttpStatus.TOO_MANY_REQUESTS.value()) {
                                rejectedRequests++
                            }
                        }
                    }
                }

                executor.shutdown()
                executor.awaitTermination(1, TimeUnit.MINUTES)

                then("HTTP 429 응답을 반환해야한다.") {
                    allowedRequests shouldBeLessThanOrEqual 50
                    rejectedRequests shouldBeGreaterThan 0
                }
            }
        }
    }
}