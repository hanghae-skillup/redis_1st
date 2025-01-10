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
@Table(name= "screen")
public class ScreenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_id")
    private Long id;

    @Column
    private String screenName; //상영관 이름

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

    @Builder
    public ScreenEntity(Long id, String screenName, Long createdBy, LocalDateTime createdAt, Long updatedBy, LocalDateTime updatedAt) {
        this.id = id;
        this.screenName = screenName;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }
}
