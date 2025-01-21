package com.example.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Theater extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "theater")
    private List<Seat> seats = new ArrayList<>();

    public Theater(String name) {
        this.name = name;
    }

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
