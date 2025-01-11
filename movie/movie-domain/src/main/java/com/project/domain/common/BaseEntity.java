package com.project.domain.common;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column
    private LocalDateTime updatedAt = LocalDateTime.now();

    private Long createdAuthor;
    private Long updatedAuthor;
}


