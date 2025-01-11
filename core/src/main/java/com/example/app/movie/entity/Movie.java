package com.example.app.movie.entity;

import com.example.app.common.BaseEntity;
import com.example.app.movie.type.MovieGenre;
import com.example.app.movie.type.MovieRating;
import com.example.app.movie.type.MovieStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tb_movie")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends BaseEntity {

    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private MovieStatus status;

    @Enumerated(EnumType.STRING)
    private MovieRating rating;

    @Enumerated(EnumType.STRING)
    private MovieGenre genre;

    private String thumbnail;

    @Column(name = "running_time")
    private int runningTime;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @OneToMany
    @JoinTable(
            name = "tb_movie_showtime",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "showtime_id"))
    private List<Showtime> showtimes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "tb_movie_theater_rel",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "theater_id"))
    private List<Theater> theaters = new ArrayList<>();
}
