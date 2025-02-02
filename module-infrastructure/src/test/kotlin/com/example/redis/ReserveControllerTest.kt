package com.example.redis

import MovieReserveRequestDtoFactory
import com.example.redis.reserve.`in`.dto.MovieReserveRequestDto
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.servlet.function.RequestPredicates.contentType
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ReserveControllerTest: BehaviorSpec() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    override fun extensions() = listOf(SpringExtension)

    init {
        this.given("Reserve") {
            val ip = "192.168.1.1"
            val threadCount = 55
            val executors = Executors.newFixedThreadPool(threadCount)
            var allowedRequests = 0
            var rejectedRequests = 0

            val reservations = CopyOnWriteArrayList<MovieReserveRequestDto>()
            reservations.addAll(MovieReserveRequestDtoFactory.fromScreening69(1L))
            reservations.addAll(MovieReserveRequestDtoFactory.fromScreening70(1L))
            reservations.addAll(MovieReserveRequestDtoFactory.fromScreening71(1L))


            `when`("영화 표 예매 API 호출 시 요청횟수가 초과되면") {
                repeat(threadCount) {
                    index -> executors.execute {
                        val response = mockMvc.post("/api/v1/movies/1/reserve") {
                            header("X-Forwarded-For", ip)
                            contentType = MediaType.APPLICATION_JSON
                            accept = MediaType.APPLICATION_JSON
                            content = objectMapper.writeValueAsString(reservations[index])
                        }.andReturn().response

                        synchronized(this) {
                            val status = response.status
                            println(status)
                            if(status == HttpStatus.OK.value()) {
                                allowedRequests++
                            } else if(status == HttpStatus.TOO_MANY_REQUESTS.value()) {
                                rejectedRequests++
                            }
                        }
                    }
                }

                executors.shutdown()
                executors.awaitTermination(1, TimeUnit.MINUTES)

                then("HTTP 429 응답을 반환해야한다.") {
                    allowedRequests shouldBeLessThanOrEqual 50
                    rejectedRequests shouldBeGreaterThan 0
                }
            }
        }
    }
}