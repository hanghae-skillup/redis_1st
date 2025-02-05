package com.study.cinema.`interface`

import com.study.cinema.application.CinemaScheduleFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/cinemas/")
class CinemaV1Controller(
    private val movieScheduleSearchFacade: CinemaScheduleFacade
) {

    @GetMapping("{cinemaId}/schedules")
    fun getSchedulesByCinemaId(
        @PathVariable cinemaId: Long
    ) = ResponseEntity.ok(
        movieScheduleSearchFacade.getSchedulesByCinemaId(cinemaId)
            .map { CinemaV1Dto.Response.MovieWithTheaterSchedule(it) }
    )
}