package com.study.cinema.application

import com.study.cinema.domain.schedule.MovieScheduleQueryService
import org.springframework.stereotype.Component

@Component
class MovieScheduleSearchFacade(
    private val movieScheduleQueryService: MovieScheduleQueryService
) {
    fun findByCinemaId(campaignId: Long) =
         movieScheduleQueryService.findByCinemaId(campaignId)
}