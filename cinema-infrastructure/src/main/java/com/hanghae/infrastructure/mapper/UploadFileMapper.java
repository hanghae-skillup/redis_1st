package com.hanghae.infrastructure.mapper;

import com.hanghae.domain.model.UploadFile;
import com.hanghae.infrastructure.entity.UploadFileEntity;

public class UploadFileMapper {
    public static UploadFile toDomain(UploadFileEntity uploadFileEntity) {
        return new UploadFile(
                uploadFileEntity.getId(),
                uploadFileEntity.getFilePath(),
                uploadFileEntity.getFileName(),
                uploadFileEntity.getOriginFileName(),
                uploadFileEntity.getCreatedBy(),
                uploadFileEntity.getCreatedAt(),
                uploadFileEntity.getUpdatedBy(),
                uploadFileEntity.getUpdatedAt()
        );
    }

    public static UploadFileEntity toEntity(UploadFile uploadFile) {
        UploadFileEntity uploadFileEntity = UploadFileEntity.builder()
                .id(uploadFile.getFileId())
                .filePath(uploadFile.getFilePath())
                .fileName(uploadFile.getFileName())
                .originFileName(uploadFile.getOriginFileName())
                .createdBy(uploadFile.getCreatedBy())
                .updatedBy(uploadFile.getUpdatedBy())
                .build();
        return uploadFileEntity;
    }
}
