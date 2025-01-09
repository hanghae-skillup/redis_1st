package com.example.domain.theater.service;

import com.example.domain.theater.Theater;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository {

    public Theater getTheaterInfo(Long theaterId);

}
