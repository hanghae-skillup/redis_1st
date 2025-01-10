package com.example.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;
    private String thumbnailUrl;
    private Integer runningTime;
    private LocalDate releaseDate;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Enumerated(value = EnumType.STRING)
    private Rating rating;

    @OneToMany(mappedBy = "movie")
    private List<MovieTheater> movieTheaters = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<Screening> screenings = new ArrayList<>();

    public Movie(String title, String thumbnailUrl, Integer runningTime, LocalDate releaseDate, Genre genre, Rating rating) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.runningTime = runningTime;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.rating = rating;
    }
}
