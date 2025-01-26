package org.example.domain.seat;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id", nullable = false)
    private Long id;

    @Column(nullable = false, name = "seat_row")
    @Enumerated(EnumType.STRING)
    private Row row;

    @Column(nullable = false, name = "seat_col")
    @Enumerated(EnumType.STRING)
    private Col col;

    @Column(nullable = false)
    private Long screenRoomId;
}
