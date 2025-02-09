package org.example.dto.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(ScreeningInfo.class)
})
@Getter
@AllArgsConstructor
public class ScreeningTimeInfo {
    private LocalDateTime startTime;
    private LocalDateTime  endTime;
}
