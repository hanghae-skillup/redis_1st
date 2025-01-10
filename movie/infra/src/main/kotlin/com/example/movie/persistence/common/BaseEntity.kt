package com.example.movie.persistence.common

import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity (
    val createdBy: String,
    val createdAt: LocalDateTime,
    val updatedBy: String,
    val updatedAt: LocalDateTime
)