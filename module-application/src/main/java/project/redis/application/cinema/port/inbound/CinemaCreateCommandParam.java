package project.redis.application.cinema.port.inbound;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import project.redis.common.SelfValidating;

@Getter
@Value
@EqualsAndHashCode(callSuper = false)
public class CinemaCreateCommandParam extends SelfValidating<CinemaCreateCommandParam> {

    @NotNull(message = "COMMON.ERROR.NOT_NULL")
    @NotBlank(message = "COMMON.ERROR.NOT_BLANK")
    String cinemaName;

    public CinemaCreateCommandParam(String cinemaName) {
        this.cinemaName = cinemaName;
        this.validate();
    }
}
