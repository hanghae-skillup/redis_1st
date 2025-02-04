package hanghae.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "screens")
@NoArgsConstructor
public class Screen extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long screenId;

    @Getter @Setter
    private String screenName;

    @OneToMany(
            mappedBy = "screen",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ScreenSchedule> screenSchedules = new ArrayList<>();

    @OneToMany(
            mappedBy = "screen",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();
}
