package com.example.movie.persistence.theater.projection

import com.example.movie.domain.theater.model.Theater
import java.time.LocalDateTime

interface TheaterProjection {
   val id: Long
   val name: String
}