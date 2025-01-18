package com.example.movie.domain.genre.model

import com.example.movie.domain.common.model.BaseModel
import java.time.LocalDateTime

class Genre(
    val id: Long,
    val name: String,
    val description: String,
    override val createdBy: String,
    override val createdAt: LocalDateTime,
    override val updatedBy: String,
    override val updatedAt: LocalDateTime
) : BaseModel