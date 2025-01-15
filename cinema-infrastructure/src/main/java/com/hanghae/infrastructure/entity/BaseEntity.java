package com.hanghae.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {
    @Column(updatable = false)
    private Long createdBy; // 작성자 ID

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt; // 작성일

    @Column
    private Long updatedBy; // 수정자 ID

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedAt; //수정일

    protected void setCreatedBy(Long createdBy) { // protected
        this.createdBy = createdBy;
    }

    protected void setUpdatedBy(Long updatedBy) { //protected
        this.updatedBy = updatedBy;
    }
}
