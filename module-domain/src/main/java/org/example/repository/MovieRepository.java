package org.example.repository;

import org.example.domain.movie.Movie;
import org.example.dto.response.movie.ScreenScheduleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("select m from Movie m where m.releaseDate < NOW()")
    List<Movie> findAllPlayingMovies();

    @Query("select new org.example.dto.response.movie.ScreenScheduleDto(ss.startTime, ss.endTime) " +
            "from ScreenSchedule ss join ss.movie m where m.id = :movieId")
    List<ScreenScheduleDto> findScreenSchedules(@Param("movieId") Long movieId);
}
