package com.example.infrastructure.screening;

import com.example.domain.screening.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreeningJpaRepository extends JpaRepository<Screening,Long> {

    @Query("select s from Screening s join fetch s.movie join fetch s.theater order by s.movie.releaseDate, s.screeningDate, s.startTime ")
    List<Screening> findAll();

    Screening findByMovieId(Long movieId);
}
