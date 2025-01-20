package com.hanghae.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;


@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name= "screening_schedule")
public class ScreeningScheduleEntity extends BaseEntity {
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
    private LocalDateTime showStartAt; // 상영 시작 시간

    @Builder
    public ScreeningScheduleEntity(Long id, MovieEntity movieEntity, ScreenEntity screenEntity, LocalDateTime showStartAt, Long createdBy, Long updatedBy) {
        this.id = id;
        this.movieEntity = movieEntity;
        this.screenEntity = screenEntity;
        this.showStartAt = showStartAt;
        this.setCreatedBy(createdBy);
        this.setUpdatedBy(updatedBy);
    }
}
