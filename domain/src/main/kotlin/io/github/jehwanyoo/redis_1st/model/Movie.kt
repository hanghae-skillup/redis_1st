package io.github.jehwanyoo.redis_1st.model

import java.time.LocalDate

data class Movie(
    val id: Long,                   // 고유 ID
    val title: String,              // 영화 제목
    val releaseDate: LocalDate,     // 개봉일
    val thumbnailUrl: String,       // 썸네일 URL
    val runtimeMinutes: Int,        // 러닝 타임 (분)
    val genre: String,              // 장르
    val rating: String,             // 영상물 등급
)
