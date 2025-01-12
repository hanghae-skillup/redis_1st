package com.bmsnc.adapter.out.persistence;

import com.bmsnc.applicaion.port.in.RunningMovieCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieTheaterInfoRepository extends JpaRepository<MovieTheaterInfo, Long> {

}
