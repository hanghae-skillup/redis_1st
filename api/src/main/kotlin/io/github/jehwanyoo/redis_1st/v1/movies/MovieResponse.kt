package io.github.jehwanyoo.redis_1st.v1.movies

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.jehwanyoo.redis_1st.model.Movie
import io.github.jehwanyoo.redis_1st.model.Screen
import io.github.jehwanyoo.redis_1st.model.ShowTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class MovieResponse(
    @JsonProperty("movie_id") val movieId: UUID,               // 영화 고유 식별자
    @JsonProperty("title") val title: String,                  // 타이틀
    @JsonProperty("rating") val rating: String,                // 영상물 등급
    @JsonProperty("genre") val genre: String,                  // 장르
    @JsonProperty("release_date") val releaseDate: LocalDate,  // 개봉일
    @JsonProperty("thumbnail_url") val thumbnailUrl: String,   // 썸네일 URL
    @JsonProperty("runtime_minutes") val runtimeMinutes: Int,  // 러닝 타임(분)
    @JsonProperty("screens") val screens: List<ScreenResponse>         // 상영관 정보 리스트
) {
    companion object {
        fun fromDomain(movies: List<Movie>): List<MovieResponse> {
            return movies.map { movie ->
                MovieResponse(
                    movieId = movie.id,
                    title = movie.title,
                    rating = movie.rating,
                    genre = movie.genre,
                    releaseDate = movie.releaseDate,
                    thumbnailUrl = movie.thumbnailUrl,
                    runtimeMinutes = movie.runtimeMinutes,
                    screens = movie.screens.map { ScreenResponse.fromDomain(it) }
                )
            }
        }
    }
}

data class ScreenResponse(
    @JsonProperty("screen_id") val screenId: UUID,                      // 상영관 고유 식별자
    @JsonProperty("screen_name") val screenName: String,                // 상영관 이름
    @JsonProperty("show_times") val showTimes: List<ShowTimeResponse>,  // 상영 시간 정보 리스트
    @JsonProperty("row") val row: Int,                                  // 좌석 행 수
    @JsonProperty("column") val column: Int,                            // 좌석 열 수
) {
    companion object {
        fun fromDomain(screen: Screen): ScreenResponse {
            return ScreenResponse(
                screenId = screen.id,
                screenName = screen.name,
                row = screen.row,
                column = screen.column,
                showTimes = screen.showTimes.map { ShowTimeResponse.fromDomain(it) }
            )
        }
    }
}

data class ShowTimeResponse(
    @JsonProperty("show_time_id") val showTimeId: UUID,        // 상영 시간 고유 식별자
    @JsonProperty("start_time") val startTime: LocalDateTime   // 상영 시작 시간
) {
    companion object {
        fun fromDomain(showTime: ShowTime): ShowTimeResponse {
            return ShowTimeResponse(
                showTimeId = showTime.id,
                startTime = showTime.startTime
            )
        }
    }
}
