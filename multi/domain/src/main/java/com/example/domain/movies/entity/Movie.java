package com.example.domain.movies.entity;

import com.example.domain.common.BaseEntity;
import com.example.domain.movies.entity.enums.Genre;
import com.example.domain.movies.entity.enums.Rating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Movies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Movie extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "rating")
    @Enumerated(EnumType.STRING)
    private Rating rating;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "running_time")
    private Integer runningTime;

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;

}
