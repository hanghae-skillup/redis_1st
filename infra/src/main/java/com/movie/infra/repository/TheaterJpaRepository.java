package com.movie.infra.repository;

import com.movie.domain.entity.Theater;
import com.movie.domain.repository.TheaterRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterJpaRepository extends JpaRepository<Theater, Long>, TheaterRepository {
    @Override
    @Query("SELECT t.name FROM Theater t WHERE t.id = :id")
    Optional<String> findNameById(@Param("id") Long id);
}