package org.example.repository;

import org.example.domain.movie.Movie;
import org.example.dto.ScreenInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieJpaRepository extends JpaRepository<Movie, Long> {
    @Query("select m from Movie m where m.releaseDate < NOW()")
    List<Movie> findAllPlayingMovies();

    @Query("select new org.example.dto.ScreenInfoProjection(sr.name, ss.startTime, ss.endTime) " +
            "from ScreenSchedule ss join ss.movie m join ss.screenRoom sr where m.id = :movieId")
    List<ScreenInfoProjection> findScreenInfos(@Param("movieId") Long movieId);
}
