package org.example.dto.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(PlayingMoviesResponseDto.class)
})
@Getter
@AllArgsConstructor
public class ScreeningInfo {
    private String screenRoomName;
    private List<ScreeningTimeInfo> screeningTimeInfos;
}
