package hanghae.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(
        name = "seats",
        uniqueConstraints = @UniqueConstraint(columnNames = {"screen_id", "seat_name"})
)
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    private String seatName;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    public void setSeatNameAndScreen(String seatName, Screen screen) {
        this.seatName = seatName;
        this.screen = screen;
    }
}
