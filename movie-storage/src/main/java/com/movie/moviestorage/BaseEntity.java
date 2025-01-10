package com.movie.moviestorage;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {

    private String createdBy;
    private String modifiedBy;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @PrePersist
    public void createdAt() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void modifiedAt() {
        modifiedAt = LocalDateTime.now();
    }

}
