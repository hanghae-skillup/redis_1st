package com.bmsnc.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select distinct s " +
            "from Schedule s " +
            "left join fetch s.movieTheaterInfo i " +
            "left join fetch i.movie m " +
            "left join fetch i.theater t " +
            "where 1=1 " +
            "and t.theaterId = :theaterId " +
            "and :now between date(s.screenOpenAt) and date(s.screenCloseAt) " +
            "order by m.movieReleaseAt desc, s.movieStartAt asc ")
    List<Schedule> getRunningMovies(@Param("theaterId") Long theaterId, @Param("now") LocalDate now);


}
