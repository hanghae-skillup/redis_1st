package com.example.movie.controller

import com.example.movie.request.ReservationRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.post
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
class ReservationControllerPessimisticLockTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {
    @Test
    fun `Lock을 적용하면 동시 요청시 충돌하면 실패 응답을 한다`() {
        val request1 = ReservationRequest(
            screeningId = 1,
            seatIds = listOf(1, 2, 3),
            userId = 1
        )
        val request2 = ReservationRequest(
            screeningId = 1,
            seatIds = listOf(1, 2, 3),
            userId = 2
        )
        val requestJson1 = objectMapper.writeValueAsString(request1)
        val requestJson2 = objectMapper.writeValueAsString(request2)

        val executor = Executors.newFixedThreadPool(3)

        val task1 = Callable {
            mockMvc.post("/api/v1/reservations") {
                contentType = MediaType.APPLICATION_JSON
                content = requestJson1
                accept = MediaType.APPLICATION_JSON
            }.andReturn()
        }

        val task2 = Callable {
            mockMvc.post("/api/v1/reservations") {
                contentType = MediaType.APPLICATION_JSON
                content = requestJson1
                accept = MediaType.APPLICATION_JSON
            }.andReturn()
        }

        val task3 = Callable {
            mockMvc.post("/api/v1/reservations") {
                contentType = MediaType.APPLICATION_JSON
                content = requestJson2
                accept = MediaType.APPLICATION_JSON
            }.andReturn()
        }

        val futures: List<Future<MvcResult>> = executor.invokeAll(listOf(task1, task2, task3))
        executor.shutdown()

        val results = futures.map { it.get() }
        val statuses = results.map { it.response.status }

        println("Thread1 status: ${statuses[0]}, responseBody: ${results[0].response.contentAsString}")
        println("Thread2 status: ${statuses[1]}, responseBody: ${results[1].response.contentAsString}")
        println("Thread3 status: ${statuses[2]}, responseBody: ${results[2].response.contentAsString}")

        assertThat(statuses).containsOnlyOnce(200)
        assertThat(statuses).contains(200, 400)
    }
}