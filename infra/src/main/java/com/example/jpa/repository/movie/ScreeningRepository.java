package com.example.jpa.repository.movie;

import com.example.jpa.entity.movie.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening,Long> {
}
