package com.example.app.booking.out.persistence.entity;

import com.example.app.movie.type.TheaterSeat;
import com.example.app.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="tb_seat")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SeatEntity extends BaseEntity {

    @Id
    @Column(name = "seat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "showtime_id")
    private Long showtimeId;

    @Column(name = "theater_id")
    private Long theaterId;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Enumerated(EnumType.STRING)
    private TheaterSeat seat;

    private boolean reserved;

    public void occupySeat(Long bookingId) {
        this.reserved = true;
        this.bookingId = bookingId;
    }
}
