package aggregate

import entity.Movie
import entity.Screen
import entity.ShowTime

data class MovieDetail(
    val movie: Movie,                // Aggregate Root
    val screens: List<Screen>,       // 상영관 배열
    val showTime: List<ShowTime>,    // 상영시간 배열
)
