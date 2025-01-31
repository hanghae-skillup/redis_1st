package org.example.domain.seat;

public enum Col {
    COL_1(1),
    COL_2(2),
    COL_3(3),
    COL_4(4),
    COL_5(5);

    private final int column;

    Col(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }
}
