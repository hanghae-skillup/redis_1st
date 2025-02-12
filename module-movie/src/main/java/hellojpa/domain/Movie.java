package hellojpa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String title; // 영화 제목

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VideoRating rating; // 영상물 등급

    @Column(nullable = false)
    private LocalDate releaseDate; // 영화 개봉일

    @Column(nullable = false)
    private String thumbnail; // 영화 썸네일

    @Column(nullable = false)
    private int runningTime; // 영화 러닝타임

    @Enumerated(EnumType.STRING)
    @JoinColumn(nullable = false)
    private Genre genre; // 영화 장르

    public Movie(String title, VideoRating rating, LocalDate releaseDate, String thumbnail, int runningTime, Genre genre) {
        this.title = title;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.thumbnail = thumbnail;
        this.runningTime = runningTime;
        this.genre = genre;
    }
}
