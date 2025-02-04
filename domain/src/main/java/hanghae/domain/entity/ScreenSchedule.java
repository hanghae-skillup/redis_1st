package hanghae.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "screen_schedule")
@NoArgsConstructor
public class ScreenSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long screenScheduleId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public ScreenSchedule(Screen screen, Schedule schedule) {
        this.screen = screen;
        this.schedule = schedule;
    }
}
