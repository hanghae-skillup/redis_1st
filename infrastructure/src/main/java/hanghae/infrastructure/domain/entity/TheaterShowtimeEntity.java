package hanghae.infrastructure.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theater_showtime")
@NoArgsConstructor
public class TheaterShowtimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theaterShowtimeId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private TheaterEntity theater;

    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    private ShowtimeEntity showtime;

    public void setTheaterAndShowtime(TheaterEntity theater, ShowtimeEntity showtime) {
        this.theater = theater;
        this.showtime = showtime;
    }
}
