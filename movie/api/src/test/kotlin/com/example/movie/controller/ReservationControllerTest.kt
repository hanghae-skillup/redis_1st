package com.example.movie.controller

import com.example.movie.dto.ReservationResponse
import com.example.movie.request.ReservationRequest
import com.fasterxml.jackson.core.type.TypeReference
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
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.Future

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
class ReservationControllerTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {
    @Test
    fun `좌석 예약에 성공하면 200 OK와 메시지를 반환한다`() {
        // given
        val request = ReservationRequest(
            screeningId = 1,
            seatIds = listOf(1, 2, 3),
            userId = 1
        )
        val jsonBody = objectMapper.writeValueAsString(request)

        // when
        val result = mockMvc.post("/api/v1/reservations") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonBody
            accept = MediaType.APPLICATION_JSON
        }.andDo {
            print()
        }.andReturn()

        // then
        assertThat(result.response.status).isEqualTo(200)
    }

    @Test
    fun `Lock을 적용하기 전에는 동시에 요청해도 다 성공하게 된다`() {
        val latch = CountDownLatch(1)
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
            latch.await()
            mockMvc.post("/api/v1/reservations") {
                contentType = MediaType.APPLICATION_JSON
                content = requestJson1
                accept = MediaType.APPLICATION_JSON
            }.andReturn()
        }

        val task2 = Callable {
            latch.await()
            mockMvc.post("/api/v1/reservations") {
                contentType = MediaType.APPLICATION_JSON
                content = requestJson1
                accept = MediaType.APPLICATION_JSON
            }.andReturn()
        }

        val task3 = Callable {
            latch.await()
            mockMvc.post("/api/v1/reservations") {
                contentType = MediaType.APPLICATION_JSON
                content = requestJson2
                accept = MediaType.APPLICATION_JSON
            }.andReturn()
        }

        latch.countDown()
        val futures: List<Future<MvcResult>> = executor.invokeAll(listOf(task1, task2, task3))
        executor.shutdown()

        val results = futures.map { it.get() }
        val statuses = results.map { it.response.status }

        println("Thread1 status: ${statuses[0]}, responseBody: ${results[0].response.contentAsString}")
        println("Thread2 status: ${statuses[1]}, responseBody: ${results[1].response.contentAsString}")
        println("Thread3 status: ${statuses[2]}, responseBody: ${results[2].response.contentAsString}")
        val responseList1 = objectMapper.readValue(
            results[0].response.contentAsString,
            object : TypeReference<List<ReservationResponse>>() {})
        val responseList2 = objectMapper.readValue(
            results[1].response.contentAsString,
            object : TypeReference<List<ReservationResponse>>() {})
        val responseList3 = objectMapper.readValue(
            results[2].response.contentAsString,
            object : TypeReference<List<ReservationResponse>>() {})

        val reservedSeat1 = responseList1.map { it.seat.id }.toHashSet()
        val reservedSeat2 = responseList2.map { it.seat.id }.toHashSet()
        val reservedSeat3 = responseList3.map { it.seat.id }.toHashSet()

        assertThat(statuses).containsOnly(200)
        assertThat(reservedSeat1 == reservedSeat2).isTrue()
        assertThat(reservedSeat2 == reservedSeat3).isTrue()
    }
}