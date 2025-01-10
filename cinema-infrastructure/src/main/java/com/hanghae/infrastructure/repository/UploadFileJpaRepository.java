package com.hanghae.infrastructure.repository;

import com.hanghae.infrastructure.entity.UploadFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileJpaRepository extends JpaRepository<UploadFileEntity, Long> {
}
