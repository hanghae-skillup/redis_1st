package com.example.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Screening extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate screeningAt;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "screening")
    private List<Seat> seats = new ArrayList<>();

    public void initializeSeats() {
        String[] rows = {"A", "B", "C", "D", "E"};
        for (String row : rows) {
            for (int i = 1; i <= 5; i++) {
                String seatNumber = row + i;
                this.seats.add(new Seat(seatNumber, this));
            }
        }
    }
}