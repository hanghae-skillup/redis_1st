package com.movie.storage.movie.repository;

import com.movie.storage.movie.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatJpaRepository extends JpaRepository<SeatEntity, Long> {

    List<SeatEntity> findByIdIn(List<Long> ids);

}
