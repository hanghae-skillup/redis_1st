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
@Table(name= "screen_seat_layout")
public class ScreenSeatLayoutEntity extends BaseEntity {
    @Id
    @Column(name = "seat_layout_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id")
    private ScreenEntity screenEntity; //상영관

    @Column(nullable = false)
    private String seatRow; //좌석 행 (알파벳)

    @Column(nullable = false)
    private Long maxSeatNumber; //최대 좌석 번호

    @Builder
    public ScreenSeatLayoutEntity(Long id, ScreenEntity screenEntity, String seatRow, Long maxSeatNumber, Long createdBy, Long updatedBy) {
        this.id = id;
        this.screenEntity = screenEntity;
        this.seatRow = seatRow;
        this.maxSeatNumber = maxSeatNumber;
        this.setCreatedBy(createdBy);
        this.setUpdatedBy(updatedBy);
    }
}
