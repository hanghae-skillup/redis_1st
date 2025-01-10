package cinema.domain

import com.redis.cinema.domain.Cinema
import com.redis.utils.BaseEntity
import jakarta.persistence.*

@Table(name = "seat")
@Entity
data class Seat(

    @Column(name = "row")
    val row: String,

    @Column(name = "column")
    val column: String,

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    val cinema: Cinema,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    val id: Long? = null
): BaseEntity() {

}