package com.example.movie;

import com.example.entity.movie.Movie;
import com.example.movie.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("SELECT new com.example.movie.dto.MoviesNowShowingDbDto(m.name, m.grade, m.releaseDate, m.thumbnail, m.runningTime, m.genre, " +
            " t.name, s.startTime, s.endTime ) " +
            "FROM Movie m " +
            "JOIN Screening s ON m.id = s.movieId " +
            "JOIN Theater t ON s.theaterId = t.id " +
            "WHERE s.startTime >= CURRENT_DATE " )
    List<MoviesNowShowingDbDto> findNowShowing(LocalDate today);

}
