package com.hanghae.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScreeningSchedule {
    //일관된 상태 유지를 위해 @AllArgsConstructor 만 사용
    private final Long scheduleId;
    private final Movie movie; // 영화
    private final Screen screen; // 상영관
    private final LocalDateTime showStartDatetime;
    private final Long createdBy; // 작성자 ID
    private final LocalDateTime createdAt; // 작성일
    private final Long updatedBy; // 수정자 ID
    private final LocalDateTime updatedAt; //수정일
}
