package com.movie.domain.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "theater")
public class Theater extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    public Theater(String name) {
        this.name = name;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void addSeat(Seat seat) {
        this.seats.add(seat);
    }

    public void initializeSeats() {
        char[] rows = {'A', 'B', 'C', 'D', 'E'};
        for (char row : rows) {
            for (int col = 1; col <= 5; col++) {
                String seatNumber = row + String.valueOf(col);
                addSeat(new Seat(seatNumber, this));
            }
        }
    }
}