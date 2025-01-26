package com.example.domain.theater.repository;


import com.example.domain.theater.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater,Long> {

}
