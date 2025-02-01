package com.example.redis

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) {

    @Test
    fun `영화 리스트 API 호출 과정에서 Rate Limit 초과 시 HTTP 429 응답을 반환해야한다`() {
        val ip = "192.168.1.1"

        repeat(15) {
            val response = mockMvc.get("/api/v1/movies") {
                header("X-Forwarded-For", ip)
            }.andReturn().response

            val status = response.status
            if(it < 10) {
                assert(status == HttpStatus.OK.value())
            } else {
                assert(status == HttpStatus.TOO_MANY_REQUESTS.value())
            }
        }
    }
}