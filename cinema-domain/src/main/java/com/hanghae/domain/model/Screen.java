package com.hanghae.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Screen {
    private final Long screenId;
    private final String screenName; //상영관 이름
    private final Long createdBy; // 작성자 ID
    private final LocalDateTime createdAt; // 작성일
    private final Long updatedBy; // 수정자 ID
    private final LocalDateTime updatedAt; //수정일
}
