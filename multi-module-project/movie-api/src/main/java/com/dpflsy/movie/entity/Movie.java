package com.dpflsy.movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private String thumbnailUrl;
    private LocalDate releaseDate;
    private Integer runtime;

    @ManyToOne
    private Rating rating;
}
