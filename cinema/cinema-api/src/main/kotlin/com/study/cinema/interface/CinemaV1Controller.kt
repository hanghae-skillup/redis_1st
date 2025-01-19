package com.study.cinema.`interface`

import com.study.cinema.application.CinemaScheduleFacade
import com.study.cinema.domain.movie.Genre
import jakarta.validation.constraints.Size
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/cinemas")
class CinemaV1Controller(
    private val movieScheduleSearchFacade: CinemaScheduleFacade
) {

    @GetMapping("/{cinemaId}/schedules")
    fun getSchedulesByCinemaId(
        @PathVariable cinemaId: Long,
        @RequestParam(required = false) genre: Genre?,
        @RequestParam(required = false) @Size(max = 100) title: String?,
    ) = ResponseEntity.ok(
        movieScheduleSearchFacade.getSchedulesByCinemaId(
            cinemaId = cinemaId,
            title = title,
            genre = genre,
        )
            .map { CinemaV1Dto.Response.MovieWithTheaterSchedule(it) }
    )
}