package com.movie.app.domain;

import java.time.LocalTime;
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

    private static final int MAX_SEATS = 25;

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
    private LocalTime screenStartTime;

    @Column
    private LocalTime screenEndTime;

    @Column
    private Boolean[] seats = new Boolean[MAX_SEATS];
    
    
    public Movie(MovieRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.rating = requestDto.getRating();
        this.releaseDate = requestDto.getReleaseDate();
        this.thumbnailImage = requestDto.getThumbnailImage();
        this.runningTime = requestDto.getRunningTime();
        this.genre = requestDto.getGenre();
        this.theater = requestDto.getTheater();
    }

    public void updateSeats(List<Integer> wantedSeats) {
        if(this.seats == null) {
            this.seats = new Boolean[MAX_SEATS];
        }

        for (int i=0; i<wantedSeats.size(); i++) {
            int val= wantedSeats.get(i);
            this.seats[val]=true;
        }
    }
}
