package org.haas.core.domain.theater;

import lombok.*;
import org.haas.core.domain.seat.Seat;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Theater {

    private Long id;

    private String name;

    private int rows;

    private int columns;

    private List<Seat> seats;



}
