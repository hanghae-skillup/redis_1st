package com.example.domain.model.entity;

import com.example.domain.exception.CustomException;
import com.example.domain.exception.ErrorCode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.Getter;

@Getter
@Entity
public class ScreeningSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Screening screening;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Seat seat;

    private boolean reserved;

    @Version private int version; // 낙관적 락 적용


    protected ScreeningSeat() {}

    private ScreeningSeat(Screening screening, Seat seat) {
        this.screening = screening;
        this.seat = seat;
        this.reserved = false;
    }

    public static ScreeningSeat of(Screening screening, Seat seat) {
        return new ScreeningSeat(screening, seat);
    }

    public void reserve() {
        if (reserved) {
            throw new CustomException(ErrorCode.SEAT_ALREADY_RESERVED);
        }
        this.reserved = true;
    }

}
