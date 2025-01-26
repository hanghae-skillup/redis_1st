package com.example.movie.persistence.reservation.entity

import com.example.movie.domain.reservation.model.Reservation
import com.example.movie.persistence.common.BaseEntity
import com.example.movie.persistence.screening.model.ScreeningEntity
import com.example.movie.persistence.seat.entity.SeatEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "reservation")
class ReservationEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", columnDefinition = "INT UNSIGNED")
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", columnDefinition = "INT UNSIGNED",
        foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val seat: SeatEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id", columnDefinition = "INT UNSIGNED",
        foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val screening: ScreeningEntity,

    val userId: Long,
    val reservationTime: LocalDateTime = LocalDateTime.now()
) : BaseEntity() {
    companion object {
        fun from(reservation: Reservation) = ReservationEntity(
            id = reservation.id,
            seat = SeatEntity.from(reservation.seat),
            screening = ScreeningEntity.from(reservation.screening),
            userId = reservation.userId,
            reservationTime = reservation.reservationTime
        ).apply {
            createdBy = reservation.createdBy
            createdAt = reservation.createdAt
            updatedBy = reservation.updatedBy
            updatedAt = reservation.updatedAt
        }
    }
    fun toDomain(): Reservation {
        return Reservation(
            id = id,
            seat = seat.toDomain(),
            screening =  screening.toDomain(),
            userId = userId,
            reservationTime = reservationTime,
            createdBy = createdBy,
            createdAt = createdAt,
            updatedBy = updatedBy,
            updatedAt = updatedAt
        )
    }
}