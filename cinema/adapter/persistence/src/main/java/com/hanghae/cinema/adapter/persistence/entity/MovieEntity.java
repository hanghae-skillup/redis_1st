package com.hanghae.cinema.adapter.persistence.entity;

import com.hanghae.cinema.domain.type.MovieGenre;
import com.hanghae.cinema.domain.type.MovieRating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "movie")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String thumbNail;

    @Enumerated(EnumType.STRING)
    private MovieRating rating;

    private LocalDate releaseAt;

    private Integer runningTimeMinute; //시간 단위 (분)

    @Enumerated(EnumType.STRING)
    private MovieGenre genre;
}
