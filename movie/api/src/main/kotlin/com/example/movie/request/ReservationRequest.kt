package com.example.movie.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class ReservationRequest(
    @field:NotNull(message = "사용자 ID는 필수입니다.")
    val userId: Long,

    @field:NotNull(message = "상영 ID는 필수입니다.")
    val screeningId: Long,

    @field:NotEmpty(message = "좌석을 선택해주세요.")
    @field:Size(max = 5, message = "최대 5개의 좌석만 예약 가능합니다.")
    val seatIds: List<Long>
)