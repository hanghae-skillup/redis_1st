package com.example.movie.domain.common.model

import java.time.LocalDateTime

interface BaseModel {
    val createdBy: String
    val createdAt: LocalDateTime
    val updatedBy: String
    val updatedAt: LocalDateTime
}