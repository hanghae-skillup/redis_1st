package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ScreenInfo {
    private String screenRoomName;
    private List<ScreenTimeInfo> screenTimeInfos;
}
