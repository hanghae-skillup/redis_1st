package org.example.repository;

import org.example.domain.movie.Genre;
import org.example.domain.movie.Movie;
import org.example.dto.MovieScreeningInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieJpaRepository extends JpaRepository<Movie, Long> {
    @Query("select new org.example.dto.MovieScreeningInfo" +
            "(m.id, m.title, m.thumbnail, m.genre, m.ageRating, m.releaseDate, m.runningTime, sr.name, ss.startTime, ss.endTime) " +
            "from Movie m " +
            "join ScreenSchedule ss on m.id = ss.movieId " +
            "join ScreenRoom sr on ss.screenRoomId = sr.id  " +
            "where (:title IS NULL OR m.title LIKE %:title%) " +
            "AND (:genre IS NULL OR m.genre = :genre) " +
            "And (m.isPlaying = true)")
    List<MovieScreeningInfo> findScreeningInfos(@Param("title") String title, @Param("genre") Genre genre);
}