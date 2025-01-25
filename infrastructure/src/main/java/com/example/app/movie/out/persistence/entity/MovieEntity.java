package com.example.app.movie.out.persistence.entity;

import com.example.app.common.BaseEntity;
import com.example.app.movie.type.MovieGenre;
import com.example.app.movie.type.MovieRating;
import com.example.app.movie.type.MovieStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tb_movie")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity extends BaseEntity {

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

    @OneToMany(mappedBy = "movie")
    private Set<ShowtimeEntity> showtimes = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<MovieTheaterEntity> movieTheaters = new HashSet<>();
}