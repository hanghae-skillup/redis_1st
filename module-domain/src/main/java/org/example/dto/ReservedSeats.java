package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.domain.seat.Col;
import org.example.domain.seat.Row;

@Getter
@AllArgsConstructor
public class ReservedSeats {
    private Row row;
    private Col col;
}
