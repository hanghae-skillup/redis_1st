package hanghae.domain.entity;

import hanghae.domain.type.ScheduleSeatId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "schedule_seat")
@NoArgsConstructor
public class ScheduleSeat {

    // 장점: 락 대상을 관라하기 깔끔함
    // 단점: 스케줄 * 상영관 수 * 25개의 데이터 삽입

    @Setter
    @EmbeddedId
    private ScheduleSeatId id = new ScheduleSeatId();

    @ManyToOne
    @MapsId("scheduleId")
    private Schedule schedule;

    @ManyToOne
    @MapsId("seatId")
    private Seat seat;

    @Getter @Setter
    private boolean reserved;

    @Version
    @Column(nullable = false)
    private Long version;

    public ScheduleSeat(Schedule schedule, Seat seat) {
        this.schedule = schedule;
        this.seat = seat;
        this.reserved = false;
    }

    public void checkDoubleBooking() {
        if (this.reserved) {
            throw new IllegalArgumentException("이미 예매된 좌석입니다.");
        }
    }
}
