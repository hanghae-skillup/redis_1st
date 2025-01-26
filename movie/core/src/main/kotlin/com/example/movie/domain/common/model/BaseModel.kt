package com.example.movie.domain.common.model

import java.time.LocalDateTime

interface BaseModel {
    val createdBy: String
    val createdAt: LocalDateTime
    var updatedBy: String
    var updatedAt: LocalDateTime

    fun update(updatedBy: String, updateAt: LocalDateTime) {
        this.updatedBy = updatedBy
        this.updatedAt = updateAt
    }
}