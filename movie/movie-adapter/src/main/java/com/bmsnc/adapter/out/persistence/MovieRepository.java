package com.bmsnc.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m from MovieTheaterInfo i " +
            "left join fetch i.movie m " +
            "left join fetch i.theater t " +
            "where 1=1 " +
            "and t.theaterId = :theaterId " +
            "and m.movieReleaseAt >= :now")
    List<Movie> getRunningMovies(@Param("theaterId") Long theaterId, @Param("now") LocalDateTime now);

}
