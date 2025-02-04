package hanghae.application.dto.response;

import hanghae.application.dto.common.MovieResponse;
import hanghae.application.dto.common.ScheduleResponse;
import hanghae.application.dto.common.ScreenResponse;
import hanghae.domain.port.MovieScheduleScreenDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MoviePlayingResponse {
    private MovieResponse movieResponse;
    private ScheduleResponse scheduleResponse;
    private ScreenResponse screenResponse;

    public static MoviePlayingResponse from(MovieScheduleScreenDto dto) {
        return MoviePlayingResponse.builder()
                .movieResponse(MovieResponse.of(dto))
                .scheduleResponse(ScheduleResponse.of(dto))
                .screenResponse(ScreenResponse.of(dto))
                .build();
    }
}

