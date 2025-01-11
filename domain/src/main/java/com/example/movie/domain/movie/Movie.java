package com.example.movie.domain.movie;

import com.example.movie.domain.common.BaseAggregateRoot;
import com.example.movie.domain.schedule.Schedule;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
@Entity
@Comment("영화")
public class Movie extends BaseAggregateRoot<Movie> {

    @Comment("제목")
    private String title;

    @Enumerated(EnumType.STRING)
    @Comment("장르")
    private MovieCategory category;

    @Enumerated(EnumType.STRING)
    @Comment("등급")
    private AgeRatingType ageRating;

    @Comment("상영 시간(min)")
    private Integer duration;

    @Comment("개봉일")
    private LocalDate releaseDate;

    @OneToOne(optional = false, mappedBy = "movie")
    @Comment("썸네일")
    private MovieThumbnail thumbnail;

    @Enumerated(EnumType.STRING)
    @Comment("상영관")
    private TheaterType theater;

    @OneToMany(mappedBy = "movie")
    private List<Schedule> schedules;

    public Movie(String title, MovieCategory category, AgeRatingType ageRating, Integer duration, LocalDate releaseDate, MovieThumbnail thumbnail, List<Schedule> schedules) {
        this.title = title;
        this.category = category;
        this.ageRating = ageRating;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.thumbnail = thumbnail;
        this.schedules = schedules;
    }

    public String getThumbnailUrl() {
        return thumbnail.url();
    }

}
