package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScreeningInfo {
    private String screenRoomName;
    private List<ScreeningTimeInfo> screeningTimeInfos;
}
