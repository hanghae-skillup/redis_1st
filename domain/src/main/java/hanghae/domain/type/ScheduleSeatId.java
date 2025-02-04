package hanghae.domain.type;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleSeatId implements Serializable {

    private Long scheduleId;

    private Long seatId;
}
