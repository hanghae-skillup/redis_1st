package com.hanghae.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScreenSeatLayout {
    private final Long seatLayoutId;
    private final Screen screen; //상영관
    private final String seatRow; //좌석 행 (알파벳)
    private final Long maxSeatNumber; //최대 좌석 번호
    private final Long createdBy; // 작성자 ID
    private final LocalDateTime createdAt; // 작성일
    private final Long updatedBy; // 수정자 ID
    private final LocalDateTime updatedAt; //수정일
}
