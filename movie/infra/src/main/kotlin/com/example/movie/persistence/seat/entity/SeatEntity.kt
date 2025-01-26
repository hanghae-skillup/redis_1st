package com.example.movie.persistence.seat.entity

import com.example.movie.domain.seat.model.Seat
import com.example.movie.persistence.common.BaseEntity
import com.example.movie.persistence.theater.entity.TheaterEntity
import jakarta.persistence.*

@Entity
@Table(name = "seat")
class SeatEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id", columnDefinition = "INT UNSIGNED")
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", columnDefinition = "INT UNSIGNED", 
        foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val theater: TheaterEntity,

    @Column(name = "row_val", nullable = false)
    val rowVal: Char,

    @Column(name = "col_val", nullable = false)
    val colVal: Int
) : BaseEntity() {
    companion object {
        fun from(seat: Seat) =SeatEntity(
            id = seat.id,
            theater = TheaterEntity.from(seat.theater),
            rowVal = seat.row,
            colVal = seat.col,
        ).apply {
            createdBy = theater.createdBy
            createdAt = seat.createdAt
            updatedBy = theater.updatedBy
            updatedAt = seat.updatedAt
        }
    }
    fun toDomain(): Seat {
        return Seat(
            id = id,
            theater = theater.toDomain(),
            row = rowVal,
            col = colVal,
            createdBy = createdBy,
            createdAt = createdAt,
            updatedBy = updatedBy,
            updatedAt = updatedAt
        )
    }
}