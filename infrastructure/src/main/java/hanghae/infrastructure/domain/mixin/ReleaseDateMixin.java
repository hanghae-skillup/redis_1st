package hanghae.infrastructure.domain.mixin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public abstract class ReleaseDateMixin {

    @JsonCreator
    public ReleaseDateMixin(
            @JsonProperty("date") LocalDate date
    ) {}
}
