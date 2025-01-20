package hanghae.infrastructure.domain.mixin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public abstract class ScheduleMixin {

    @JsonCreator
    public ScheduleMixin(
       @JsonProperty("startDateTime") LocalDateTime startDateTime,
       @JsonProperty("endDateTime") LocalDateTime endDateTime,
       @JsonProperty("baseDateTime") LocalDateTime baseDateTime
    ) {}
}
