package com.example.movie.persistence.theater.repository

import com.example.movie.persistence.theater.entity.TheaterEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TheaterJpaRepository : JpaRepository<TheaterEntity, Long>