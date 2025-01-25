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
            name = "reserve_group_id_idx",
            columnNames = ["reserve_group_id", "screening_id", "seat_id"]
        )
    ]
)
@Entity
class ReservationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_id")
    val id: Long? = null,

    @Column(name = "reserve_group_id")
    val reserveGroupId: String,

    @Column(name = "user_id")
    val userId: Long,

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
                reserveGroupId = reservation.reserveGroupId,
                userId = reservation.userId,
                screening = screening,
                seat = seat
            )
        }
    }
}