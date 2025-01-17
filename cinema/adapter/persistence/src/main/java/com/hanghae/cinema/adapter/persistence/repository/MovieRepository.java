package com.hanghae.cinema.adapter.persistence.repository;

import com.hanghae.cinema.adapter.persistence.entity.MovieEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    List<MovieEntity> findAllByReleaseAtLessThanEqual(LocalDate date, Sort sort);
}
