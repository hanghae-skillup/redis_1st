package com.example.movie.repository;

import com.example.movie.entity.movie.Movie;
import com.example.movie.repository.dto.MoviesNowShowingDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("SELECT new com.example.movie.repository.dto.MoviesNowShowingDetailDto(m.id, m.name, m.grade, m.releaseDate, m.thumbnail, m.runningTime, m.genre, " +
            "t.id, t.name, s.startAt, s.endAt) " +
            "FROM Movie m " +
            "JOIN Screening s ON m.id = s.movieId " +
            "JOIN Theater t ON s.theaterId = t.id " +
            "WHERE s.startAt >= :now")
    List<MoviesNowShowingDetailDto> findNowShowing(LocalDateTime now);

}
