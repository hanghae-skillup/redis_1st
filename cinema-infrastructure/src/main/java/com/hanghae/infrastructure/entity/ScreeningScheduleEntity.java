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
@Table(name= "screening_schedule")
public class ScreeningScheduleEntity {
    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private MovieEntity movieEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id")
    private ScreenEntity screenEntity;

    @Column
    private LocalDateTime showStartDatetime; // 상영 시작 시간

    @Column
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
    public ScreeningScheduleEntity(Long id, MovieEntity movieEntity, ScreenEntity screenEntity, LocalDateTime showStartDatetime, Long createdBy, LocalDateTime createdAt, Long updatedBy, LocalDateTime updatedAt) {
        this.id = id;
        this.movieEntity = movieEntity;
        this.screenEntity = screenEntity;
        this.showStartDatetime = showStartDatetime;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }
}
