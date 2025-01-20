package com.hanghae.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UploadFile {
    //일관된 상태 유지를 위해 @AllArgsConstructor 만 사용
    private final Long fileId; 
    private final String filePath; // 파일 경로
    private final String fileName; // 파일 이름
    private final String originFileName; // 원본 파일 이름
    private final Long createdBy; // 작성자 ID
    private final LocalDateTime createdAt; // 작성일
    private final Long updatedBy; // 수정자 ID
    private final LocalDateTime updatedAt; //수정일
}
