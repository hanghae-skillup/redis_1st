package com.study.cinema.application

import com.study.cinema.domain.schedule.MovieScheduleQueryService
import org.springframework.stereotype.Component

@Component
class CinemaScheduleFacade(
    private val movieScheduleQueryService: MovieScheduleQueryService
) {
    fun getSchedulesByCinemaId(campaignId: Long) =
         movieScheduleQueryService.findByCinemaId(campaignId)
}