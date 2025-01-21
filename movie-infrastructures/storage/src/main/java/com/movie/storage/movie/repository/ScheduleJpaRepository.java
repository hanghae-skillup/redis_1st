package com.movie.storage.movie.repository;

import com.movie.storage.movie.entity.ScheduleEntity;
import com.movie.storage.movie.entity.TheaterEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleJpaRepository extends JpaRepository<ScheduleEntity, Long> {

    @EntityGraph(attributePaths = {"theater", "screen", "movie"}, type = EntityGraph.EntityGraphType.LOAD)
    List<ScheduleEntity> findByTheater(TheaterEntity theater);

}
