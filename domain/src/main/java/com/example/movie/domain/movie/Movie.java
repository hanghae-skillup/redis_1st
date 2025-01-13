package com.example.movie.domain.movie;

import static com.google.common.base.Preconditions.checkArgument;
import static jakarta.persistence.CascadeType.DETACH;

import com.example.movie.common.domain.BaseAggregateRoot;
import com.example.movie.domain.schedule.Schedule;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @Column(nullable = false, length = MAX_TITLE_LENGTH)
    private String title;

    @Enumerated(EnumType.STRING)
    @Comment("장르")
    @Column(nullable = false)
    private MovieCategory category;

    @Enumerated(EnumType.STRING)
    @Comment("등급")
    @Column(nullable = false)
    private AgeRatingType ageRating;

    @Comment("상영 시간(min)")
    @Column(nullable = false)
    private Integer duration;

    @Comment("개봉일")
    @Column(nullable = false)
    private LocalDate releaseDate;

    @OneToOne(optional = false, mappedBy = "movie")
    @Comment("썸네일")
    private MovieThumbnail thumbnail;

    @Enumerated(EnumType.STRING)
    @Comment("상영관")
    @Column(nullable = false)
    private TheaterType theater;

    @OrderBy("startAt ASC")
    @OneToMany(mappedBy = "movie", cascade = DETACH)
    private List<Schedule> schedules = new ArrayList<>();

    public static final int MAX_TITLE_LENGTH = 50;

    public Movie(String title, MovieCategory category, AgeRatingType ageRating, Integer duration, LocalDate releaseDate, MovieThumbnail thumbnail) {
        checkArgument(title != null, "title must be provided.");
        checkArgument(!title.isBlank(), "title must be provided.");
        checkArgument(title.length() <= MAX_TITLE_LENGTH, "title length must be less or equals than %s.", MAX_TITLE_LENGTH);

        checkArgument(category != null, "category must be provided.");
        checkArgument(ageRating != null, "ageRating must be provided.");
        checkArgument(duration != null, "duration must be provided.");
        checkArgument(releaseDate != null, "releaseDate must be provided.");
        checkArgument(thumbnail != null, "thumbnail must be provided.");

        this.title = title;
        this.category = category;
        this.ageRating = ageRating;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.thumbnail = thumbnail;
    }

    public String getThumbnailUrl() {
        return thumbnail.url();
    }
}
