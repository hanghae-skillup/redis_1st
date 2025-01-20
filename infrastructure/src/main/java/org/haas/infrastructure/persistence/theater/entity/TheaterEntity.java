package org.haas.infrastructure.persistence.theater.entity;

import jakarta.persistence.*;
import lombok.*;
import org.haas.core.domain.seat.Seat;
import org.haas.infrastructure.persistence.seat.entity.SeatEntity;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "theaters")
@Entity
public class TheaterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int rows;

    private int columns;

    @OneToMany(mappedBy = "theater")
    private List<SeatEntity> seats = new ArrayList<SeatEntity>();



}
