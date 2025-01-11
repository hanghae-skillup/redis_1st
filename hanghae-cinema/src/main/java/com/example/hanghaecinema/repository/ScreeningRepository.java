package com.example.hanghaecinema.repository;

import com.example.hanghaecinema.domain.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Query("""
            SELECT s FROM Screening s
            JOIN FETCH s.movie m
            WHERE s.showDate >= :date
            ORDER BY m.releaseDate DESC
            """)
    List<Screening> findAllScreeningByShowDateAfter(@Param("date") LocalDate date);
}
