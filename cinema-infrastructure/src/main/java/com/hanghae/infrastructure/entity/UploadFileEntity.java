package com.hanghae.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name= "upload_file")
public class UploadFileEntity extends BaseEntity {
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

    @Builder
    public UploadFileEntity(Long id, String filePath, String fileName, String originFileName, Long createdBy, Long updatedBy) {
        this.id = id;
        this.filePath = filePath;
        this.fileName = fileName;
        this.originFileName = originFileName;
        this.setCreatedBy(createdBy);
        this.setUpdatedBy(updatedBy);
    }
}
