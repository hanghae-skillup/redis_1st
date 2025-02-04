package com.example.domain.model.entity;

import com.example.domain.converter.ContentRatingConverter;
import com.example.domain.converter.GenreConverter;
import com.example.domain.model.base.AuditingFields;
import com.example.domain.model.valueObject.ContentRating;
import com.example.domain.model.valueObject.Genre;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
public class Movie extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String title;
    @Convert(converter = ContentRatingConverter.class)
    @Column(nullable = false) private ContentRating contentRating;
    @Column(nullable = false) private LocalDate releaseDate;
    @Column(nullable = false) private String thumbnailUrl;
    @Column(nullable = false) private int runtimeMinutes;
    @Convert(converter = GenreConverter.class)
    @Column(nullable = false) private Genre genre;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @OrderBy("startTime ASC")
    private List<Screening> screenings = new ArrayList<>();


    protected Movie() {}

    private Movie(String title,
                  ContentRating contentRating,
                  LocalDate releaseDate,
                  String thumbnailUrl,
                  int runtimeMinutes,
                  Genre genre) {
        this.title = title;
        this.contentRating = contentRating;
        this.releaseDate = releaseDate;
        this.thumbnailUrl = thumbnailUrl;
        this.runtimeMinutes = runtimeMinutes;
        this.genre = genre;
    }

    public static Movie of(String title,
                           ContentRating contentRating,
                           LocalDate releaseDate,
                           String thumbnailUrl,
                           int runtimeMinutes,
                           Genre genre) {
        return new Movie(title, contentRating, releaseDate, thumbnailUrl, runtimeMinutes, genre);
    }

}
