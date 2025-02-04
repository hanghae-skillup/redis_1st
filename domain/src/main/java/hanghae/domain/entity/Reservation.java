package hanghae.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(
        name = "reservations",
        indexes = {
                @Index(name = "idx_schedule_id_member_id", columnList = "schedule_id, member_id")
        }
)
@NoArgsConstructor
public class Reservation extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Getter
    @Setter
    @OneToMany(
            mappedBy = "reservation",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ReservationSeat> reservationSeats;


    public static Reservation of(Member member, Schedule schedule) {
        Reservation reservation = new Reservation();
        reservation.member = member;
        reservation.schedule = schedule;
        reservation.reservationSeats = new ArrayList<>();

        return reservation;
    }

    public LocalDateTime getStartDateTime() {
        return schedule.getStartDateTime();
    }

    public LocalDateTime getEndDateTime() {
        return schedule.getEndDateTime();
    }

    public Long getMemberId() {
        return member.getMemberId();
    }

    public Long getScheduleId() {
        return schedule.getScheduleId();
    }
}
