package com.movie.infra.repository;

import com.movie.domain.entity.Schedule;
import com.movie.domain.repository.ScheduleRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleJpaRepository extends JpaRepository<Schedule, Long>, ScheduleRepository {

    @Override
    @Query("SELECT s FROM Schedule s JOIN FETCH s.movie JOIN FETCH s.theater")
    @EntityGraph(attributePaths = {"movie", "theater"})
    List<Schedule> findAllFetchMovieTheater();
}