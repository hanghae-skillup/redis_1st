package com.redis.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Embeddable
import jakarta.persistence.OneToMany
import org.hibernate.annotations.BatchSize
import java.util.Comparator

@Embeddable
open class ScreeningSchedules(

    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "movie", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val screeningSchedules: MutableList<ScreeningSchedule> = mutableListOf()
) {

    fun gets(): MutableList<ScreeningSchedule> {
        return screeningSchedules
    }

    fun getsStartTimeOrderByDesc(): MutableList<ScreeningSchedule> {
        return screeningSchedules.stream().sorted(Comparator.comparing(ScreeningSchedule::startTime)).toList()
    }
}