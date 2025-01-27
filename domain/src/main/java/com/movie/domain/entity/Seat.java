package com.movie.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Seat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String seatNumber;

    private Long theaterId;

    public Seat(String seatNumber, Long theaterId) {
        this.seatNumber = seatNumber;
        this.theaterId = theaterId;
    }
} 