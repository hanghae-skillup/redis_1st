package com.hanghae.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Member {
    //일관된 상태 유지를 위해 @AllArgsConstructor 만 사용
    private final Long memberId;
    private final LocalDate birthDate; // 생년월일
    private final Long createdBy; // 작성자 ID
    private final LocalDateTime createdAt; // 작성일
    private final Long updatedBy; // 수정자 ID
    private final LocalDateTime updatedAt; //수정일
}
