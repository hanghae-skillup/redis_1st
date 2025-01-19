package com.movie.infra.repository;

import com.movie.domain.entity.Theater;
import com.movie.domain.repository.TheaterRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterJpaRepository extends JpaRepository<Theater, Long>, TheaterRepository {
}