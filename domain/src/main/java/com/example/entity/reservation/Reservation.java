package com.example.entity.reservation;

import com.example.entity.BaseEntity;
import com.example.entity.movie.Screening;
import com.example.entity.movie.Seats;
import com.example.entity.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id")
    private Screening screening;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReservedSeat> reservedSeats = new ArrayList<>();

    @Builder
    public Reservation(User user, Screening screening) {
        this.user = user;
        this.screening = screening;
    }

    public void reservation(Seats seats) {
        if (seats.isSizeExceedingLimit()) {
            throw new IllegalArgumentException("5개 이상의 좌성은 예약할 수 없습니다.");
        }
        if (!seats.isContinuousSeat()) {
            throw new IllegalArgumentException("좌석 예매는 연속적인 좌석만 예매 가능합니다.");
        }
    }

    public void addReservedSeat(List<ReservedSeat> reservedSeats) {
        this.reservedSeats.addAll(reservedSeats);
    }
}
