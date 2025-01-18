package com.example.movie.request

import com.example.movie.domain.screening.model.ScreeningStatus
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size

data class MovieSearchRequest(
    @field:Valid
    val status: ScreeningStatus,

    @field:Size(max = 100, message = "제목은 100자를 초과할 수 없습니다")
    val title: String?,

    @field:Min(value = 1, message = "장르 ID는 1 이상이어야 합니다")
    val genreId: Long?
)