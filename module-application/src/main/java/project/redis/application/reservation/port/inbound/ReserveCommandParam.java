package project.redis.application.reservation.port.inbound;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import project.redis.common.SelfValidating;

@Getter
@Value
@EqualsAndHashCode(callSuper = false)
public class ReserveCommandParam extends SelfValidating<ReserveCommandParam> {

    @NotNull
    @Size(min = 1, max = 5)
    List<UUID> seatIds;

    @NotNull
    UUID screeningId;

    @NotNull
    String userName;

    public ReserveCommandParam(List<UUID> seatIds, UUID screeningId, String userName) {
        this.seatIds = seatIds;
        this.screeningId = screeningId;
        this.userName = userName;
        validate();
    }
}
