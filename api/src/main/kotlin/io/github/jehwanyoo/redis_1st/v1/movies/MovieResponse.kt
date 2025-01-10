package io.github.jehwanyoo.redis_1st.v1.movies

import com.fasterxml.jackson.annotation.JsonProperty
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
    @JsonProperty("screens") val screens: List<Screen>         // 상영관 정보 리스트
)

data class Screen(
    @JsonProperty("screen_id") val screenId: UUID,             // 상영관 고유 식별자
    @JsonProperty("screen_name") val screenName: String,       // 상영관 이름
    @JsonProperty("show_times") val showTimes: List<ShowTime>, // 상영 시간 정보 리스트
    @JsonProperty("row") val row: Int,                         // 좌석 행 수
    @JsonProperty("column") val column: Int,                   // 좌석 열 수
)

data class ShowTime(
    @JsonProperty("show_time_id") val showTimeId: UUID,        // 상영 시간 고유 식별자
    @JsonProperty("start_time") val startTime: LocalDateTime   // 상영 시작 시간
)
