package com.example.movie.repository;

import com.example.movie.entity.movie.Genre;
import com.example.movie.entity.movie.Movie;
import com.example.movie.repository.dto.MoviesDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("SELECT new com.example.movie.repository.dto.MoviesDetailDto(m.id, m.name, m.grade, m.releaseDate, m.thumbnail, m.runningTime, m.genre, " +
            "t.id, t.name, s.startAt, s.endAt) " +
            "FROM Movie m " +
            "JOIN Screening s ON m.id = s.movieId " +
            "JOIN Theater t ON s.theaterId = t.id " +
            "WHERE (:genre IS NULL OR m.genre = :genre) " +
            "AND (:search IS NULL OR LOWER(m.name) LIKE LOWER(CONCAT('%', :search, '%')))")
    List<MoviesDetailDto> findAll(@Param("now") LocalDateTime now,
                                         @Param("genre") Genre genre,
                                         @Param("search") String search);

}
