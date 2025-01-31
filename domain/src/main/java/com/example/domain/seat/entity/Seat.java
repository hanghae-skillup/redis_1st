package com.example.domain.seat.entity;

import com.example.domain.BaseEntity;
import com.example.domain.theater.entity.Theater;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Seat")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String seatCol;

    @Column(nullable = false)
    private String seatRow;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @Version // Optimistic Lock 버전 필드
    private Integer version;

    public void updateStatus(Status status) {
        this.status = status;
    }


    public enum Status{
        AVAILABLE, RESERVED,OCCUPIED, OUT_OF_ORDER
    }
}
