package com.example.jpa.entity.movie;

import com.example.jpa.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Movie extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String thumbnail;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    private LocalDate releaseDate;

    private Long runningTime;

}
