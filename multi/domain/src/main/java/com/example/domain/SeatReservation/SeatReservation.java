package com.example.domain.SeatReservation;

import com.example.domain.common.BaseEntity;
import com.example.domain.screening.Screening;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SeatReservations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatReservation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_reservation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private Screening screening;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "is_reserved")
    private boolean isReserved;


}
