package com.example.demo.domain.repository

import com.example.demo.domain.entity.Screening
import org.springframework.data.jpa.repository.JpaRepository

interface ScreeningRepository : JpaRepository<Screening, Long> 