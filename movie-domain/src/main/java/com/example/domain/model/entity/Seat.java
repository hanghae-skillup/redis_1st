package com.example.domain.model.entity;

import com.example.domain.model.base.AuditingFields;
import com.example.domain.model.valueObject.SeatNumber;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
public class Seat extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded private SeatNumber seatNumber;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Theater theater;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
    private List<ScreeningSeat> screeningSeats;


    protected Seat() {}

    private Seat(SeatNumber seatNumber) {
        this.seatNumber = seatNumber;
    }

    public static Seat of(SeatNumber seatNumber) {
        return new Seat(seatNumber);
    }

}
