package hanghae.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor
public class Schedule extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @OneToMany(
            mappedBy = "schedule",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ScreenSchedule> screenSchedules = new HashSet<>();

    @Setter
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    public void setStartDateTimeAndEndDateTime(
            LocalDateTime startDateTime,
            LocalDateTime endDateTime
    ) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
