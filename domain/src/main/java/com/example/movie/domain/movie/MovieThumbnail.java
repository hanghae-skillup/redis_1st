package com.example.movie.domain.movie;

import com.example.movie.domain.common.BaseAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Comment("영화 이미지")
public class MovieThumbnail extends BaseAggregateRoot<MovieThumbnail> {

    @OneToOne(optional = false)
    private Movie movie;

    @Comment("URL")
    String url;

    @Comment("경로")
    String path;

    public MovieThumbnail(Movie movie, String url, String path) {
        this.movie = movie;
        this.url = url;
        this.path = path;
    }
}
