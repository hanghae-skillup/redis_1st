package com.example.app.booking.out.persistence.entity;

import com.example.app.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="tb_booking")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity extends BaseEntity {

    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "showtime_id")
    private Long showtimeId;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "theater_id")
    private Long theaterId;

    @Column(name = "total_seats")
    private Integer totalSeats;
}
