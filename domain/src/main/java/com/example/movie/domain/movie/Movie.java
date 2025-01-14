package com.example.movie.domain.movie;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static jakarta.persistence.CascadeType.DETACH;
import static jakarta.persistence.ConstraintMode.NO_CONSTRAINT;

import com.example.movie.common.domain.BaseAggregateRoot;
import com.example.movie.domain.schedule.Schedule;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "uk_movie_movie_thumbnail_id", columnNames = "thumbnail_id")
})
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
    private Integer durationMin;

    @Comment("개봉일")
    @Column(nullable = false)
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    @Comment("상영관")
    @Column(nullable = false)
    private TheaterType theater;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "thumbnail_id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    private MovieThumbnail thumbnail;

    @OrderBy("startAt ASC")
    @OneToMany(mappedBy = "movie", cascade = DETACH)
    private List<Schedule> schedules = new ArrayList<>();

    public static final int MAX_TITLE_LENGTH = 50;

    public Movie(String title, MovieCategory category, AgeRatingType ageRating, Integer durationMin, LocalDate releaseDate, TheaterType theater, MovieThumbnail thumbnail) {
        checkNotNull(title, "title must be provided.");
        checkArgument(!title.isBlank(), "title must be provided.");
        checkArgument(title.length() <= MAX_TITLE_LENGTH, "title length must be less or equals than %s.", MAX_TITLE_LENGTH);
        checkNotNull(category, "category must be provided.");
        checkNotNull(ageRating, "ageRating must be provided.");
        checkNotNull(durationMin, "duration must be provided.");
        checkArgument(durationMin > 0, "duration must be greater than 0.");
        checkNotNull(releaseDate, "releaseDate must be provided.");
        checkNotNull(theater, "theater must be provided.");
        checkNotNull(thumbnail, "thumbnail must be provided.");

        this.title = title;
        this.category = category;
        this.ageRating = ageRating;
        this.durationMin = durationMin;
        this.releaseDate = releaseDate;
        this.theater = theater;
        this.thumbnail = thumbnail;
    }

    public String getThumbnailUrl() {
        return thumbnail.url();
    }
}
