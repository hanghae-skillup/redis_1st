package com.example.entity.reservation;

import com.example.entity.BaseEntity;
import com.example.entity.movie.Screening;
import com.example.entity.movie.Seats;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id")
    private Screening screening;

    @OneToMany(mappedBy = "reservation")
    private List<ReservedSeat> reservedSeats = new ArrayList<>();

    @Builder
    public Reservation(Screening screening) {
        this.screening = screening;
    }

    public void reservation(Seats seats) {
        if (seats.isSizeExceedingLimit()) {
            throw new IllegalArgumentException("5개 이상의 좌성은 예약할 수 없습니다.");
        }
    }
}
