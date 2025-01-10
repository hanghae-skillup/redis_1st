package com.hanghae.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name= "upload_file")
public class UploadFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column
    private String filePath; // 파일 경로

    @Column
    private String fileName; // 파일 이름

    @Column
    private String originFileName; // 원본 파일 이름

    @Column(nullable = false)
    private Long createdBy; // 작성자 ID

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt; // 작성일

    @Column
    private Long updatedBy; // 수정자 ID

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedAt; //수정일

    @Builder
    public UploadFileEntity(Long id, String filePath, String fileName, String originFileName, Long createdBy, LocalDateTime createdAt, Long updatedBy, LocalDateTime updatedAt) {
        this.id = id;
        this.filePath = filePath;
        this.fileName = fileName;
        this.originFileName = originFileName;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }
}
