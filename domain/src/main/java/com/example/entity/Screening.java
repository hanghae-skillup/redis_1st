package com.example.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Screening extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;

    private LocalDate screeningAt;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Screening screening = (Screening) object;
        return Objects.equals(id, screening.id) && Objects.equals(screeningAt, screening.screeningAt) && Objects.equals(startedAt, screening.startedAt) && Objects.equals(endedAt, screening.endedAt) && Objects.equals(movie, screening.movie) && Objects.equals(seats, screening.seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, screeningAt, startedAt, endedAt, movie, seats);
    }
}