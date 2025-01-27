package com.example.domain.seatReservation.entity;

import com.example.domain.common.entity.BaseEntity;
import com.example.domain.screening.entity.Screening;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SeatReservations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class SeatReservation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_reservation_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private Screening screening;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "is_reserved")
    private boolean isReserved;



    public static List<SeatReservation> makeReservation(int count, List<String> seats, Screening screening, Long userId){
        List<SeatReservation> reservations = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            reservations.add(
                    SeatReservation.builder()
                            .userId(userId)
                            .seatNumber(seats.get(i))
                            .screening(screening)
                            .isReserved(true)
                            .build()
            );
        }
        return reservations;
    }

}
