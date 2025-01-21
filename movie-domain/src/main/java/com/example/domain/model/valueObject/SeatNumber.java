package com.example.domain.model.valueObject;

import static java.util.Objects.hash;

import jakarta.persistence.Embeddable;

@Embeddable
public class SeatNumber {

    private String seatRow;
    private int seatColumn;

    protected SeatNumber() {}

    private SeatNumber(String seatRow, int seatColumn) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
    }

    public static SeatNumber of(String row, int column) {
        return new SeatNumber(row, column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeatNumber that)) return false;
        return seatColumn == that.seatColumn && seatRow.equals(that.seatRow); // 좌석 번호가 같은 경우 동일한 객체로 취급
    }

    @Override
    public int hashCode() {
        return hash(seatRow, seatColumn);
    }

    @Override
    public String toString() {
        return seatRow + seatColumn;
    }

}
