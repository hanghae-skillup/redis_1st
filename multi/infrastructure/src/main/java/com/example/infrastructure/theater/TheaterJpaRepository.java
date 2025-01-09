package com.example.infrastructure.theater;

import com.example.domain.theater.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterJpaRepository extends JpaRepository<Theater,Long> {
}
