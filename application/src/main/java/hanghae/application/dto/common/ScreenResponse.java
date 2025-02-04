package hanghae.application.dto.common;

import hanghae.domain.entity.Screen;
import hanghae.domain.port.MovieScheduleScreenDto;

public record ScreenResponse(
        String screenName
) {

    public static ScreenResponse from(Screen screen) {
        return new ScreenResponse(
                screen.getScreenName()
        );
    }

    public static ScreenResponse of(MovieScheduleScreenDto movieScheduleScreenDto) {
        return new ScreenResponse(
                movieScheduleScreenDto.screenName()
        );
    }
}
