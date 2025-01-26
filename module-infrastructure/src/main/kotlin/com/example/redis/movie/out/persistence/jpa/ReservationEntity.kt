package com.example.redis.movie.out.persistence.jpa

import com.example.redis.cmmn.BaseEntity
import com.example.redis.movie.Reservation
import com.example.redis.theater.out.persistence.jpa.SeatEntity
import jakarta.persistence.*
import java.util.UUID

@Table(
    name = "reserve",
    uniqueConstraints = [
        UniqueConstraint(
            name = "reserve_unique_id_idx",
            columnNames = ["screening_id", "seat_id"]
        )
    ]
)
@Entity
class ReservationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_id")
    val id: Long? = null,

    @Column(name = "reserve_receipt_id")
    var reserveReceiptId: String? = null,

    @Column(name = "user_id")
    var userId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id")
    val screening: ScreeningEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    val seat: SeatEntity,

): BaseEntity() {

    companion object {
        fun fromDomain(reservation: Reservation, screening: ScreeningEntity, seat: SeatEntity): ReservationEntity {
            return ReservationEntity(
                reserveReceiptId = reservation.reserveReceiptId,
                userId = reservation.userId,
                screening = screening,
                seat = seat
            )
        }
    }
}