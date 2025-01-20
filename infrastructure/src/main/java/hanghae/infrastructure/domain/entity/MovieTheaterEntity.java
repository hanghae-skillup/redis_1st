package hanghae.infrastructure.domain.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie_theater")
@NoArgsConstructor
public class MovieTheaterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieTheaterId;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private TheaterEntity theater;

    public void setMovieAndTheater(MovieEntity movie, TheaterEntity theater) {
        this.movie = movie;
        this.theater = theater;
    }
}
