package com.movie.domain.entity;

import jakarta.persistence.Table;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "movie")
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String grade;
    private String genre;
    private Integer runningTime;
    private LocalDate releaseDate;
    private String thumbnailUrl;

    protected Movie() {}
}