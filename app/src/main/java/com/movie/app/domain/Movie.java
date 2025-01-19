package com.movie.app.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name="Movie")
public class Movie extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String rating;

    @Column(nullable = false)
    private String releaseDate;

    @Column(nullable = false)
    private String thumbnailImage;

    @Column(nullable = false)
    private String runningTime;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String theater;

    @Column
    private List<String> screeningSchedule;
    
    public Movie(MovieRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.rating = requestDto.getRating();
        this.releaseDate = requestDto.getReleaseDate();
        this.thumbnailImage = requestDto.getThumbnailImage();
        this.runningTime = requestDto.getRunningTime();
        this.genre = requestDto.getGenre();
        this.theater = requestDto.getTheater();
        this.screeningSchedule = requestDto.getScreeningSchedule();
    }
}
