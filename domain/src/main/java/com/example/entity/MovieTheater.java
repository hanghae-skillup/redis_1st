package com.example.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieTheater extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    public MovieTheater(Movie movie, Theater theater) {
        this.movie = movie;
        this.theater = theater;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MovieTheater that = (MovieTheater) object;
        return Objects.equals(id, that.id) && Objects.equals(movie, that.movie) && Objects.equals(theater, that.theater);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, theater);
    }
}