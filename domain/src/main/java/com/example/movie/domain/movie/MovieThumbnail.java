package com.example.movie.domain.movie;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static jakarta.persistence.ConstraintMode.NO_CONSTRAINT;
import static jakarta.persistence.FetchType.LAZY;

import com.example.movie.common.domain.BaseAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @OneToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "movie_id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Movie movie;

    @Comment("URL")
    @Column(nullable = false, length = MAX_URL_LENGTH)
    String url;

    @Comment("경로")
    @Column(nullable = false, length = MAX_PATH_LENGTH)
    String path;

    public static final int MAX_URL_LENGTH = 255;
    public static final int MAX_PATH_LENGTH = 255;

    public MovieThumbnail(String url, String path) {
        checkNotNull(url, "url must be provided.");
        checkArgument(!url.isBlank(), "url must be not blank.");
        checkArgument(url.length() <= MAX_URL_LENGTH, "url length must be less or equals than %s.", MAX_URL_LENGTH);
        checkArgument(UrlValidator.getInstance().isValid(url), "url must be valid.");
        checkNotNull(path, "path must be provided.");
        checkArgument(!path.isBlank(), "path must be not blank.");
        checkArgument(path.length() <= MAX_PATH_LENGTH, "path length must be less or equals than %s.", MAX_PATH_LENGTH);

        this.url = url;
        this.path = path;
    }

    public void connectParent(Movie movie) {
        this.movie = movie;
    }
}
