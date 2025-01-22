package hellojpa.domain;

import hellojpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Seat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater; // 상영관

    @Column(name = "seat_row", nullable = false)
    private String seatRow; // 상영관 행(ex. A, B...)

    @Column(name = "seat_column", nullable = false)
    private int seatColumn; // 상영관 열(ex. 1, 2...)

}
