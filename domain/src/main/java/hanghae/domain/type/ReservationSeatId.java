package hanghae.domain.type;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ReservationSeatId implements Serializable {

    private Long reservationId;

    private Long seatId;
}
