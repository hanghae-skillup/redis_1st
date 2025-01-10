package io.github.jehwanyoo.redis_1st.aggregate

import io.github.jehwanyoo.redis_1st.entity.Movie
import io.github.jehwanyoo.redis_1st.entity.Screen
import io.github.jehwanyoo.redis_1st.entity.ShowTime

data class MovieDetail(
    val movie: Movie,                // Aggregate Root
    val screens: List<Screen>,       // 상영관 배열
    val showTimes: List<ShowTime>,   // 상영시간 배열
)
