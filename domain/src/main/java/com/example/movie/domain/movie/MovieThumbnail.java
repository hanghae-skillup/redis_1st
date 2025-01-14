package com.example.movie.domain.movie;

import static com.google.common.base.Preconditions.checkArgument;
import static jakarta.persistence.ConstraintMode.NO_CONSTRAINT;

import com.example.movie.common.domain.BaseAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.validator.routines.UrlValidator;
import org.hibernate.annotations.Comment;

@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "uk_movie_movie_thumbnail_id", columnNames = "movie_id")
})
@Entity
@Comment("영화 썸네일")
public class MovieThumbnail extends BaseAggregateRoot<MovieThumbnail> {

    @OneToOne(optional = false)
    @JoinColumn(name = "movie_id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Movie movie;

    @Comment("URL")
    @Column(nullable = false, length = MAX_URL_LENGTH)
    String url;

    @Comment("경로")
    @Column(nullable = false)
    String path;

    public static final int MAX_URL_LENGTH = 255;

    public MovieThumbnail(Movie movie, String url, String path) {
        checkArgument(movie != null, "movie must be provided.");
        checkArgument(url != null, "url must be provided.");
        checkArgument(url.length() <= MAX_URL_LENGTH, "url length must be less or equals than %s.", MAX_URL_LENGTH);
        checkArgument(UrlValidator.getInstance().isValid(url), "url must be valid.");

        this.movie = movie;
        this.url = url;
        this.path = path;
    }

    public void connectParent(Movie movie) {
        this.movie = movie;
    }
}
