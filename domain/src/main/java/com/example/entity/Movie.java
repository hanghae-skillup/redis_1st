package com.example.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
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
    private Set<MovieTheater> movieTheaters = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<Screening> screenings = new HashSet<>();

    public Movie(String title, String thumbnailUrl, Integer runningTime, LocalDate releaseDate, Genre genre, Rating rating) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.runningTime = runningTime;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.rating = rating;
    }
}
