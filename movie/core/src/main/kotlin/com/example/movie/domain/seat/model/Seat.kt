package com.example.movie.domain.seat.model

import com.example.movie.domain.common.model.BaseModel
import com.example.movie.domain.theater.model.Theater
import java.time.LocalDateTime

class Seat(
    val id: Long,
    val theater: Theater,
    val row: Char,
    val col: Int,
    override val createdBy: String,
    override val createdAt: LocalDateTime,
    override var updatedBy: String,
    override var updatedAt: LocalDateTime
) : BaseModel