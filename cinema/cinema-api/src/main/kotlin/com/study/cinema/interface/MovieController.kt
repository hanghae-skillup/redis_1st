package com.study.cinema.`interface`

import com.study.cinema.application.MovieScheduleSearchFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/movies")
class MovieController(
    private val movieScheduleSearchFacade: MovieScheduleSearchFacade
) {

    @GetMapping("{cinemaId}/theater-schedules")
    fun findTheaterScheduleByCinemaId(
        @PathVariable cinemaId: Long
    ) = ResponseEntity.ok(
        movieScheduleSearchFacade.findByCinemaId(cinemaId)
            .map { MovieV1Dto.Response.MovieWithTheaterSchedule(it) }
    )
}