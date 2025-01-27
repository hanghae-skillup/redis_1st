package com.example.domain.model.entity;

import com.example.domain.model.base.AuditingFields;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
public class Reservation extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Screening screening;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReservedSeat> reservedSeats = new ArrayList<>();


    protected Reservation() {}

    private Reservation(Screening screening, Member member) {
        this.screening = screening;
        this.member = member;
    }

    public static Reservation create(Screening screening, Member member) {
        return new Reservation(screening, member);
    }

    public void addReservedSeats(List<ReservedSeat> seats) {
        this.reservedSeats.addAll(seats);
    }

}
