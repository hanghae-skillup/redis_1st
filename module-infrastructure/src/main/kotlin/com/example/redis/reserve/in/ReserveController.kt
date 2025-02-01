package com.example.redis.reserve.`in`

import com.example.redis.movie.`in`.mapper.MovieControllerMapper
import com.example.redis.reserve.`in`.dto.MovieReserveRequestDto
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v1/movies")
class ReserveController(
    private val reserveFacade: ReserveFacade
) {

    @PostMapping("/{movieId}/reserve")
    fun reserve(@PathVariable movieId: Long, @Valid @RequestBody body: MovieReserveRequestDto): ResponseEntity<Unit> {
        val groupId = this.reserveFacade.reserve(movieId, MovieControllerMapper.toReservationDomain(body))

        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(groupId)
            .toUri()

        val headers = HttpHeaders()
        headers.location = location

        return ResponseEntity(headers, HttpStatus.CREATED)
    }
}