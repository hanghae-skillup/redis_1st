package com.example.domain.seat.entity;

import com.example.domain.screening.entity.Screening;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Seat")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer seatNumber;

    @ManyToOne
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;
}
