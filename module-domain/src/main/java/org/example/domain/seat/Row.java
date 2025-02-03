package org.example.domain.seat;

import lombok.Getter;

@Getter
public enum Row {
    ROW_A("A"),
    ROW_B("B"),
    ROW_C("C"),
    ROW_D("D"),
    ROW_E("E");

    private final String row;

    Row(String row) {
        this.row = row;
    }

    public String getRow() {
        return row;
    }
}
